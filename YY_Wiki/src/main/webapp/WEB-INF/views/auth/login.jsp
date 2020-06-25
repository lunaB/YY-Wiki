<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ include file="../include/header.jsp"%>
<section class="">
	<div class="p-md-5 p-3">
		<h1>로그인</h1>
	</div>
	<div class="row justify-content-center align-self-center">
		<form name="login" action="/auth/loginProcess" method="POST" class="col-md-7 col-sm-10 py-md-5" style="max-width: 600px;">
			<div class="form-group row">
				<label for="user_id" class="col-sm-4 col-form-label">Username</label>
				<div class="col-sm-12">
					<input type="text" class="form-control" id="user_id" name="user_id" placeholder="Username">
				</div>
				<label for="user_pw" class="col-sm-4 col-form-label">Password</label>
				<div class="col-sm-12">
					<input type="password" class="form-control" id="user_pw" name="user_pw" placeholder="Password">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<div class="form-check">
						<label class="form-check-label">
							<input class="form-check-input" type="checkbox">로그인 유지
						</label>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="submit" class="btn btn-primary w-100">Sign in</button>
				</div>
			</div>
			<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
		</form>
	</div>
</section>
<%@ include file="../include/footer.jsp"%>