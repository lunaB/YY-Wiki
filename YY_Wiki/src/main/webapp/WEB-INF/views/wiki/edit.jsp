<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<section style="width:100%; display:table-cell;" class="h-100">
	<ul class="nav nav-tabs pt-4" role="tablist">
		<li class="nav-item" onclick="edit_preview_btn_click(this)"><a class="nav-link active" href="#preview"
			role="tab" data-toggle="tab">미리보기</a></li>
		<li class="nav-item"><a class="nav-link" href="#editer"
			role="tab" data-toggle="tab">편집기</a></li>
	</ul>
	<form action="/edit/${ wiki.title }" method="post" class="w-100 h-100">
		<div class="tab-content h-100">
			<div role="tabpanel" class="tab-pane fade show active" id="preview" style="height:100%;">
				<div class="py-4 px-3" id="doc">
					<h1>${ wiki.title }</h1>
					<span class="badge badge-success">기능준비중</span>
				</div>
				<sub class="float-right px-3">
					마지막 편집 :: <fmt:formatDate value="${ wiki.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</sub>
				<div class="py-4 px-md-3 px-sm-0" id="content">
				</div>
			</div>
			<div role="tabpanel" class="tab-pane fade h-100" id="editer">
				<textarea id="editText" name="content" class="form-control h-100" style="overflow:visible; border-top:0px; min-height: 600px; border-top-right-radius:0px; border-top-left-radius:0px;">${ wiki.content }</textarea>
			</div>
		</div>
		<hr>
		<div class="border border-primary rounded p-3 my-3">
			<label for="summary">Summary</label>
			<input type="text" name="summary" id="summary" placeholder="본인이 편집한 내용을 간추린 설명을 서술해주세요."
				class="form-control" aria-describedby="summaryInfo" required pattern="^[\S\s]{1,20}$" title="수정한 내용에 대하여 요약해주세요.(20자 이내)" autocomplete="false">
			<p id="summaryInfo" class="form-text text-muted">신중하게 생각하고 편집하시길 바랍니다.</p>
			<div style="float:left; margin-top: -10px;">
				<div class="g-recaptcha" data-sitekey="6Lc8OpsUAAAAAMSW_yTqFy9LNyEEa7uUh5gzW8Rf"></div>
			</div>
			<div class="text-right mt-5">
				<input type="submit" value="편집완료" class="btn btn-success">
				<a href="/wiki/${ wiki.title }" class="btn btn-danger">편집취소</a>
			</div>
		</div>
		<input type="hidden" name="wiki_id" value="${ wiki.wiki_id }">
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
	<div id="hiddenData" class="d-none">
		<textarea id="hiddenContent" class="d-none">${ wiki.content }</textarea>
	</div>
</section>

<%@ include file="../include/footer.jsp"%>