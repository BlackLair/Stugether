<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스투 회원가입</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap" class="d-flex">
		<div id="introDiv" class="box-half">
		</div>
		<div id="joinDiv" class="box-half d-flex align-items-center justify-content-center">
			<div class="col-2"></div>
			<div class="col-8">
				<div class="h1 my-5">회원 가입</div>
				<div class="single-input-row">
					<div class="form-floating col-8">
						<input id="idInput" type="text" class="form-control" placeholder="">
						<label for="idInput">아이디를 입력하세요.</label>
					</div>
					<div class="mx-3">
						<button id="duplicatedIdBtn" type="button" class="form-control" disabled="true">중복 확인</button>
					</div>
				</div>
				<div class="single-input-row">
					<div class="form-floating col-8">
						<input id="passwordInput" type="password" class="form-control" placeholder="">
						<label for="passwordInput">패스워드를 입력하세요.</label>
					</div>
					<div class="mx-3">
					</div>	
				</div>
				<div class="single-input-row">
					<div class="form-floating col-8">
						<input id="confirmPasswordInput" type="password" class="form-control" placeholder="">
						<label for="confirmPasswordInput">패스워드를 확인하세요.</label>
					</div>
					<div class="mx-3">
					</div>	
				</div>
				<div class="single-input-row">
					<div class="form-floating col-8">
						<input id="nicknameInput" type="text" class="form-control" placeholder="">
						<label for="nicknameInput">닉네임을 입력하세요.</label>
					</div>
					<div class="mx-3">
						<button id="duplicatedNicknameBtn" type="button" class="form-control" disabled="true">중복 확인</button>
					</div>	
				</div>
				<div class="single-input-row">
					<div class="form-floating col-8">
						<input id="emailInput" type="email" class="form-control" placeholder="">
						<label for="emailInput">이메일을 입력하세요.</label>
					</div>
					<div class="mx-3">
					</div>	
				</div>
				<div class="col-8">
					<button id="joinBtn" type="button" class="form-control my-4" disabled="true">회원 가입</button>
					<div style="font-size:13px;">이미 회원이신가요?</div>
					<button onClick="location.href = '/user/login-page';" id="loginPageBtn" type="button" class="form-control my-1">로그인</button>
				</div>
			</div>
		</div>
	</div>


<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

<script src="/static/js/user/join-validation.js"></script>
<script src="/static/js/user/join.js"></script>
</body>
</html>