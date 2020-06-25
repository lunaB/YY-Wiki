<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!-- charset -->
	<meta charset="UTF-8"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<!-- google search -->
	<c:if test="${ isMainPage == true }">
		<meta name="google-site-verification" content="KF6qFTG8olmBrSAbluP2KQU6iadJ1xhh01CeseUXVxg" />
	</c:if>
	<!-- viewport -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
	<!-- summary -->
	<meta property="og:title" content="양디위키"/>
	<meta property="og:type" content="website"/>
	<meta property="og:url" content="http://yywiki.lunab.xyz"/>
	<!-- <meta property="og:image" content=""/> -->
	<c:if test="${ not empty wiki }">
		<meta property="og:description" content="${ wiki.title }"/>
	</c:if>
	<c:if test="${ empty wiki }">
		<meta property="og:description" content="&#34;${ value }&#34;문서가 존재하지 않습니다."/>
	</c:if>
	<!-- css -->
	<link href="/resources/css/fontawesome.min.css" rel="stylesheet">
	<link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="/resources/css/wurischool-wiki.css" rel="stylesheet"/>
    <link href="/resources/css/style.css" rel="stylesheet"/>
	<!-- javascript -->
	<script src="/resources/js/jquery-3.3.1.min.js" type="text/javascript"></script>
	<script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="/resources/js/wurischool-wiki.js" type="text/javascript"></script>
	
	<script src="/resources/js/common.js" type="text/javascript"></script>
	
	<!-- adsense -->
	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
    <script>
      (adsbygoogle = window.adsbygoogle || []).push({
        google_ad_client: "ca-pub-6365733490327128",
        enable_page_level_ads: true
      });
    </script>
    <!-- recaptcha -->
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
	<script>
		function validate(event) {
			grecaptcha.execute();
		}
		
		function onload() {
			var element = document.getElementById('logoutSubmit');
			element.onclick = validate;
		}
	</script>
	<!-- common.js in footer-->
	<title><c:if test="${ not empty wiki.title }">${ wiki.title }</c:if> :: 양디위키</title>
</head>
<body class="h-100" style="background-color: #EEE;">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="d-none">
		<form name="logout" action="/auth/logout" method="post">
			<input type="hidden" 
				name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<input type="submit" value="로그아웃" id="logoutSubmit"/>
		</form>
	</div>
	<div class="container col-sm-12 col-md-12 col-lg-10">
		<a class="navbar-brand" href="/">양디위키</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="/wiki/양디위키:대문">대문
						<span class="sr-only">(current)</span>
					</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">게시판</a></li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">최근</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">최근생성</a>
						<a class="dropdown-item" href="#">최근변경</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">패치목록</a>
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link disabled" href="#">개발자</a>
				</li>
			</ul>
			<div class="form-inline my-2 my-lg-0">
				<span>
					<input class="form-control form-control-sm mr-sm-2" id="searchText" name="searchText" type="text" placeholder="검색어를 입력해주세요.">
					<input class="btn btn-sm btn-outline-success my-2 my-sm-0" id="searchBtn" type="button" value="검색" />
				<span>
			</div>
			<ul class="navbar-nav">
				<li class="nav-item dropdown">
					<span class="nav-link dropdown-toggle btn mx-1" id="loginMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="fas fa-user"></i>
						<sec:authorize access="isAuthenticated()">
							<span class="text-dark font-weight-bold"><sec:authentication property="principal.name"/></span>
						</sec:authorize>
					</span>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
						<sec:authorize access="isAnonymous()">
							<a class="dropdown-item" href="/auth/login">로그인</a>
							<a class="dropdown-item" href="/auth/register">회원가입</a>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
							<label class="dropdown-item d-block m-0" for="logoutSubmit">로그아웃</label>
						</sec:authorize>
					</div>
				</li>
			</ul>
		</div>
	</div>
</nav>
<c:if test="${ not empty loginSucceed }">
	<div class="alert alert-success alert-dismissible fade show m-0" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
	    </button>
		<strong>로그인</strong> 로그인에 성공하였습니다.
	</div>
</c:if>
<c:if test="${ not empty loginFailed }">
	<div class="alert alert-danger alert-dismissible fade show m-0" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
	    </button>
	    <strong>로그인</strong> 로그인에 실패하였습니다.
	</div>
</c:if>
<c:if test="${ not empty registerSucceed }">
	<div class="alert alert-success alert-dismissible fade show m-0" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
	    </button>
		<strong>회원가입</strong> 회원가입에 성공하였습니다.
	</div>
</c:if>
<c:if test="${ not empty registerFailed }">
	<div class="alert alert-danger alert-dismissible fade show m-0" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
	    </button>
	    <strong>회원가입</strong> 회원가입에 실패하였습니다.
	</div>
</c:if>
<div class="container col-sm-12 col-md-12 col-lg-10 border-left border-right" style="display:table; background-color: #FFF; min-height: calc(100% - 58px);">
