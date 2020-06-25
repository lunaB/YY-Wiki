// 문서 수정시 칸 크기 증가
function textarea_resize(obj) {
	obj.style.height = "1px";
	obj.style.height = (12+obj.scrollHeight)+"px";
}

// 문서수정
function edit_preview_btn_click() {
	var mark = document.getElementById("editText");
	if(mark === undefined) return;
	var lexerTokens = lexer.process(mark.value);
    var output = document.getElementById("content");
    output.innerHTML = parser.process( lexerTokens );
}

// 문서 로드
function loadDocument() {
	// 위키 로드
	var mark = document.getElementById("hiddenContent");
	if(mark === null) return;
	var lexerTokens = lexer.process(mark.value);
    var output = document.getElementById("content");
    output.innerHTML = parser.process( lexerTokens );
    
}
$(function(){
	
	loadDocument()
	
	// 검색 버튼 이벤트
	$("#searchBtn").click(function(){
    	location.href = "/search/" + encodeURI($("#searchText").val());
	});
});
