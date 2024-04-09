<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스투 로그인</title>
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
				<form id="loginForm">
					<div class="h1 my-5">로그인</div>
					<div class="single-input-row">
						<div class="form-floating col-8">
							<input id="idInput" type="text" class="form-control" placeholder="">
							<label for="idInput">아이디</label>
						</div>
						<div class="mx-3">
						</div>
					</div>
					<div class="single-input-row">
						<div class="form-floating col-8">
							<input id="passwordInput" type="password" class="form-control" placeholder="">
							<label for="passwordInput">패스워드</label>
						</div>
						<div class="mx-3">
						</div>	
					</div>
					<div class="col-8">
						<button id="loginBtn" type="submit" class="form-control my-4">로그인</button>
						<div style="font-size:13px;">계정이 없으신가요?</div>
						<button onClick="location.href = '/user/join-page';" id="joinPageBtn" type="button" class="form-control my-1">회원 가입</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

<script src="/static/js/user/login.js"></script>

</body>
</html>