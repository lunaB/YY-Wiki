<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<section>
	<div class="py-3 px-3">
		<c:if test="${ not empty wiki }">
			<h1>${ wiki.title }</h1>
			<c:if test="${ permission_id >= wiki.permission_id }">
				<a href="/edit/${ wiki.title }" style="float:right" class="text-info">[위키 편집]</a>
			</c:if>
			<c:if test="${ permission_id < wiki.permission_id }">
				<span style="float:right" class="text-info">본 문서의 편집 권한이 없습니다.</span>
			</c:if>
			<span class="badge badge-success mx-2">기능준비중</span>
		</c:if>
		<c:if test="${ empty wiki }">
			<h1>&quot;<c:out value="${ value }"/>&quot;에 해당하는 문서가 없습니다.</h1>
			<span class="badge badge-success">메롱</span>
		</c:if>
	</div>
	<sub class="float-right px-3">
		마지막 편집 :: <fmt:formatDate value="${ wiki.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
	</sub>
	<c:if test="${ isMainPage }">
		<div class="pt-2 px-md-3 px-sm-0" id="mainPageContent">
			<div class="w-100 row m-0">
				<div class="col-md-4 col-lg-4 col-sm-12 p-0">
					<span class="card m-2" style="border-radius: 0px;">
						<ul class="list-group list-group-flush">
							<li class="list-group-item">
								<strong>최근 업데이트된 문서</strong><br>
								<sub>최근 업데이트 (<fmt:formatDate value="${ updatePage[0].regdate }" pattern="yyyy-MM-dd HH:mm:ss"/>)</sub>
							</li>
							<li class="list-group-item p-0 list-group">
								<c:forEach var="updateItem" items="${ updatePage }">
									<a href='<c:url value="/wiki/${ updateItem.title_url }"/>' class="list-group-item list-group-item-action justify-content-between link py-2">
										<c:out value="${ updateItem.title_entity }" escapeXml="true"/>
										<c:if test="${ updateItem.count <= 1 }">
											<span class="badge badge-success badge-pill mx-1">추가됨</span>
										</c:if>
										<c:if test="${ updateItem.count > 1 }">
											<span class="badge badge-info badge-pill mx-1">수정됨</span>
										</c:if>
									</a>
								</c:forEach>
							</li>
						</ul>
					</span>
				</div>
				<div class="col-md-4 col-lg-4 col-sm-12 p-0">
					<span class="card m-2" style="border-radius: 0px;">
						<ul class="list-group list-group-flush">
							<li class="list-group-item">
								<strong>추천하는 문서</strong><br>
								<sub>렌덤</sub>
							</li>
							<li class="list-group-item p-0 list-group">
								<c:forEach var="recommendItem" items="${ recommendPage }">
									<a href='<c:url value="/wiki/${ recommendItem.title_url }"/>' class="list-group-item list-group-item-action justify-content-between link py-2">
										<c:out value="${ recommendItem.title_entity }" escapeXml="true"/>
									</a>
								</c:forEach>
							</li>
						</ul>
					</span>
				</div>
			</div>
		</div>
	</c:if>
	<div class="py-4 px-md-3 px-sm-0" id="content">
		<c:if test="${ not empty wiki and wiki.content == null }">
			문서에 내용이 비어있습니다!! 작성해주세요. 
			<a class="btn btn-info btn-sm" href='<c:url value="/edit/${ wiki.title }"/>'>추가하기</a>
		</c:if>
		<c:if test="${ empty wiki }">
			<form action="/wiki/${ value }/append" method="POST">
				찾는 문서가 없으시다면 새로 만들어 보시는것은 어떻습니까? 
				<div style="float:right">
					<div class="g-recaptcha" data-sitekey="6Lc8OpsUAAAAAMSW_yTqFy9LNyEEa7uUh5gzW8Rf"></div>
				</div>
				<input type="submit" class="btn btn-info btn-sm text-white" value="문서 만들기">
				<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
			</form>
		</c:if>
	</div>
	<div id="hiddenData" class="d-none">
		<c:if test="${ not empty wiki.content }">
			<textarea id="hiddenContent">${ wiki.content }</textarea>
		</c:if>
		<input id="title" value="${ wiki.title }"/>
	</div>
	<!-- 하단 -->
    <center>
        <ins class="adsbygoogle"
             style="display:block;"
             data-ad-client="ca-pub-6365733490327128"
             data-ad-slot="2725860878"
             data-ad-format="link"
             data-full-width-responsive="false"></ins>
        <script>
        (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
    </center>
</section>

<%@ include file="../include/footer.jsp" %>