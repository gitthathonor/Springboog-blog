<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<br>

<div class="container">
	<form action="/users/${users.id}/update" method="post">
		<div class="mb-3">
			수정할 비밀번호 : <input type="password" class="form-control" placeholder="Enter password" name="password" value="${users.password}">
		</div>
		<div class="mb-3">
			수정할 이메일 : <input type="email" class="form-control" placeholder="Enter email" name="email" value="${users.email}">
		</div>
		<button type="submit" class="btn btn-primary">회원정보수정</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>

