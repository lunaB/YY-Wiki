/* 우리학교 위키엔진 */
/* wuri school wiki engine */
/* lunaB */

/* test */

function start() {
    var mark = document.getElementById("textarea");
    var lexerTokens = lexer.process(mark.value);
    var output = document.getElementById("output");
    output.innerHTML = parser.process( lexerTokens );
}

/* real test */
//window.onload = function() {
//	var mark = document.getElementById("hiddenContent");
//	if(mark === null) return;
//	var lexerTokens = lexer.process(mark.value);
//    var output = document.getElementById("content");
//    output.innerHTML = parser.process( lexerTokens );
//}

var lexer = new Lexer();

function Lexer() {

    /*
        - 문법가이드 -
        
        # abc           &#35;
        ## abc
        ### abc
        - abc           &#45;
         - abc
          - abc
        ** abc **       &#42;
        [[ abc ]]       &#91; &#93;
        ~~ abc ~~       &#126;
        __ abc __       &#95;
        // abc //       &#47;
    */
    
    // [정규식] 검출 순위별로 나열한다.
    var regExp = {
        
        // 우선적 처리
        important: {
            // 이스케이프
            escape: /\\/,    
        },
        // 라인검출
        line: {
            title: /^((#){1,3})\s(.+)$/, // 타이틀
            list: /^((\s){0,3})-\s(.+)$/, // 리스트
        },
        // 고정 ( 라인검출, 마지막으로 로드해야함 )
        static: {
            contents: /^&#58;&#58;[\s]{0,1}목차[\s]{0,1}&#58;&#58;$/, // 자동생성 목차
        },
        // 텍스트검출 ( 감싸는 문법 )
        cover: {
            bold: /\*\*(.+?)\*\*/g, // 두껍게
            localLink: /\[\[(.+?)\]\]/g, // 로컬 링크
            strikethrough: /\~\~(.+?)\~\~/g, // 취소선
            underline: /__(.+?)__/g, // 밑줄
            italic: /\/\/(.+?)\/\//g, // 기울게 
        },
        // 일반 텍스트 ( 감싸는 문법에서 검출될 첫 특수기호 추가 )
        common: {
            //text: /^.+?(?=[\"\[\~\_\/])/
        }
        // 함수
        // 변수
    };
    
    function xssfilter( text ) {
//    	var res = text
//    		.replace(/;/g, "&#59;")
//    		.replace(/</g, "&#60;")
//    		.replace(/>/g, "&#62;")
//    		.replace(/\\/g, "&#92;");
//    	return res;
        return text;
    }
    
    // [메인] 
    this.process = function( text ) {
    	
    	console.log(text);
    	
        var lines = text.trim().split( "\n" );
        var tokens = [];
        var idx = 0;
        
        while( idx < lines.length ) {
            var line = lines[idx];
            var idxc = 0;
            
            var block;
            
            /* 라인검출 */
            if( block = regExp.line.title.exec( line ) ) {
                tokens.push({
                    type: 'title',
                    level: block[1].length, // /5, // &#35; 5글자기 때문에 나눔
                    // target: block[2],
                    value: block[3]
                });
            }
            else if( block = regExp.line.list.exec( line ) ) {
                tokens.push({
                    type: 'list',
                    level: block[1]+1, //.length+1, // 공백이 0개부터 시작
                    // target: block[2],
                    value: block[3]
                });
            }
            
            /* 예약어 검출 */
            else if( block = regExp.static.contents.exec( line ) ) {
                tokens.push({
                    type: 'contents'
                });
            }
            
            /* 일반문자열 */
            else {
                var token = [];
                
                while( block = regExp.cover.bold.exec( line ) ) {
                    token.push({
                        type: 'bold',
                        first: block.index,
                        end: block.index+block[0].length,
                        value: block[1]
                    });
                }
                while( block = regExp.cover.localLink.exec( line ) ) {
                    token.push({
                        type: 'localLink',
                        first: block.index,
                        end: block.index+block[0].length,
                        value: block[1]
                    });
                }
                while( block = regExp.cover.strikethrough.exec( line ) ) {
                    token.push({
                        type: 'strikethrough',
                        first: block.index,
                        end: block.index+block[0].length,
                        value: block[1]
                    });
                }
                while( block = regExp.cover.underline.exec( line ) ) {
                    token.push({
                        type: 'underline',
                        first: block.index,
                        end: block.index+block[0].length,
                        value: block[1]
                    });
                }
                while( block = regExp.cover.italic.exec( line ) ) {
                    token.push({
                        type: 'italic',
                        first: block.index,
                        end: block.index+block[0].length,
                        value: block[1]
                    });
                }
                
                if( token.length > 0 ) {
                    token.sort( function( a, b ) {
                        return a.first < b.first ? -1 : 1;
                    });
                    
                    var nTmp = 0;
                    token.forEach( function( e ) {
                        tokens.push({
                            type: 'text',
                            value: line.substring(nTmp, e.first)
                        });
                        nTmp = e.end;
                        tokens.push({
                            type: e.type,
                            value: e.value
                        });
                    });
                    if(nTmp < line.length){
                        tokens.push({
                            type: 'text',
                            value: line.substring(nTmp, line.length)
                        });
                    }
                }else {
                    if(line.trim().length != 0) {
                        tokens.push({
                            type: 'text',
                            value: line.substring(0, line.length)
                        });
                    }
                }
                if(line.trim().length != 0) {
                    tokens.push({
                        type: 'nextLine'
                    });
                }
            }
            idx++;
        }
    
        console.log("---------------------------------------");
        tokens.forEach( function( e ) {
        	if(e.value !== undefined){
        		e.value = xssfilter(e.value);
        	}
            console.log(e);
        });
        console.log(tokens);
        return tokens;
    }
}

var parser = new Parser();
function Parser() {
    
    var tokenData;
    
    this.process = function( tokens ) {
        
//        tokens.forEach( function( e ) {
//          ... 
//        });
        
        var html = "";
        
        tokenData = tokens;
        
        tokens.forEach( function( e ) {
            html += renderHtml( e );
        });
        
        return html;
    }
    
    function renderHtml( e ) {
        switch( e.type ) {
            /* line */
            case 'title':
                return renderer.title( e.value, e.level );
            case 'list':
                return renderer.list( e.value, e.level );
            /* static */
            case 'contents':
                var list = [];
                tokenData.forEach( function( e ) {
                    if(e.type === "title") {
                        list.push( e );
                    }
                });
                return renderer.contents( list );
            /* cover */
            case 'bold':
                return renderer.bold( e.value );
            case 'localLink':
                return renderer.localLink( e.value );
            case 'strikethrough':
                return renderer.strikethrough( e.value );
            case 'underline':
                return renderer.underline( e.value );
            case 'italic':
                return renderer.italic( e.value );
            /* text */
            case 'text':
                return renderer.text( e.value );
            /* next line */
            case 'nextLine':
                return renderer.nextLine();
        }
    }
    
}

var renderer = new Renderer();
function Renderer() {
    this.title = function( value, level ) {
        return '<span class="ww-texthead ww-texthead-level-'+level+'" id="ww-title-"'+level+'>'+value+'</span>';
    }
    this.list = function( value, level ) {
        return '<span class="ww-list ww-list-level-'+level+'">'+value+'</span>';
    }
    this.contents = function( list ) {
        var html = `
            <span class="ww-contents">
                <span class="ww-contents-title">목차</span>
                <div class="ww-contents-item-wrap">
            `;
        list.forEach( function( e ) {
            html += `
                    <span class="ww-contents-item ww-contents-item-level-`+e.level+`">`+e.value+`</span>
            `;
        });
        html += `
                </div>
            </span>
        `;
        return html;
    }
    this.bold = function( value ) {
        return '<span class="ww-bold">'+value+'</span>';
    }
    this.localLink = function( value ) {
        return '<a class="ww-link" href="/wiki/'+encodeURI(value)+'">'+value+'</a>';
    }
    this.strikethrough = function( value ) {
        return '<span class="ww-strikethrough">'+value+'</span>';
    }
    this.underline = function( value ) {
        return '<span class="ww-underline">'+value+'</span>';
    }
    this.italic = function( value ) {
        return '<span class="ww-italic">'+value+'</span>';
    }
    this.text = function( value ) {
        return value;
    }
    this.nextLine = function() {
        return '<br>';
    }
}
