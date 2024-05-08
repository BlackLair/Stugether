<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정 관리</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
		<section class="p-3 bg-dark d-flex justify-content-center">
			<div class="bg-light w-100 p-3">
				<div class="w-100 text-center">
					<h2>계정 관리</h2>
				</div>
				<div class="w-100 text-end">
					<div>본인인증 만료 시간 : ${expireTimeStr }</div>
				</div>
				<hr>
				<div class="d-flex w-100 justify-content-around">
					<div class="w-50 d-flex flex-column align-items-center">
						<div class="w-100 text-center my-2">
							<h4>패스워드 변경</h4>
						</div>
						<div class="d-flex w-75 justify-content-center align-items-center my-2">
							<div class="w-25 text-center">
								패스워드
							</div>
							<input id="passwordInput" type="password" class="w-75 form-control">
						</div>
						<div class="d-flex w-75 justify-content-center align-items-center my-2">
							<div class="w-25 text-center">
								패스워드 확인
							</div>
							<input id="confirmPasswordInput" type="password" class="w-75 form-control">
						</div>
						<div class="d-flex w-75 justify-content-end my-4">
							<button id="passwordBtn" type="button" class="btn btn-primary" disabled="true" >변경</button>
						</div>
						<div style="height:1px; width:100%;" class="bg-dark"></div>
						<div class="w-100 text-center my-2">
							<h4>닉네임 변경</h4>
						</div>
						<div class="d-flex w-75 justify-content-center align-items-center my-2">
							<div class="w-25 text-center">
								현재 닉네임
							</div>
							<div class="w-75 fw-bold">${nickname }</div>
						</div>
						<div class="d-flex w-75 justify-content-center align-items-center my-2 flex-nowrap">
							<div style="width:25%;" class="text-center">
								새 닉네임
							</div>
							<input id="nicknameInput" type="text" style="width:70%;" class="form-control">
							<button id="duplicatedNicknameBtn" type="button" style="width:140px;" class="btn btn-primary" disabled="true">중복 확인</button>
						</div>
						<div class="d-flex w-75 justify-content-end my-4">
							<button id="nicknameBtn" type="button" class="btn btn-primary" disabled="true">변경</button>
						</div>
					</div>
					<div style="width:1px;" class="bg-dark mx-2"></div>
					<div class="w-50 d-flex flex-column align-items-center">
						<div class="w-100 text-center my-2">
							<h4>이메일 변경</h4>
						</div>
						<div class="d-flex w-75 justify-content-center align-items-center my-3">
							<div class="w-25 text-center">
								현재 이메일
							</div>
							<div class="w-75 fw-bold">${email }</div>
						</div>
						<div class="d-flex w-75 justify-content-center align-items-center my-2">
							<div class="w-25 text-center">
								새 이메일
							</div>
							<input id="emailInput" type="text" class="w-75 form-control" >
						</div>
						<div class="d-flex w-75 justify-content-end my-4">
							<button id="emailBtn" type="button" class="btn btn-primary" disabled="true">변경</button>
						</div>
						<div style="height:1px; width:100%;" class="bg-dark"></div>
						<div class="d-flex w-75 justify-content-center align-items-center my-2">
							<div class="h4">회원 탈퇴</div>
						</div>
						<div class="w-100 my-2 text-center">
							탈퇴 시 작성한 모든 게시물 및 댓글이 삭제됩니다.<br>
							회원 탈퇴를 하려면 아래 문장을 입력 후 탈퇴 버튼을 클릭하세요.
						</div>
						<div data-login-id="${loginId }" class="w-100 my-2 text-center fst-italic fw-bolder">
							${loginId } 회원탈퇴하겠습니다.
						</div>
						<div class="w-100 d-flex align-items-center justify-content-center">
							<input data-login-id="${loginId }" id="resignInput" type="text" class="form-control w-75 mx-3">
							<button id="resignBtn" type="button" class="btn btn-danger" disabled="true">탈퇴</button>
						</div>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="/static/js/user/join-validation.js" ></script>
<script src="/static/js/userMgmt/config.js"></script>
</body>
</html>