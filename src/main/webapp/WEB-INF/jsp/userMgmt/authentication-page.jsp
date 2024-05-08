<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정 관리 - 본인 인증</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
		<section class="p-3 bg-dark d-flex justify-content-center">
			<main class="bg-white d-flex flex-column align-items-center justify-content-center">
				<div>
					계정 관리를 하려면 패스워드를 입력하세요.
				</div>
				<form id="passwordForm">
					<div class="d-flex justify-content-around align-items-center my-5">
						<div>패스워드</div>
						<div class="mx-3">
							<input id="passwordInput" type="password" class="form-control">
						</div>
						<button id="passwordBtn" type="submit" class="btn btn-primary">확인</button>
					</div>
				</form>
			</main>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="/static/js/common/common.js"></script>
<script>
	$(document).ready(function(){
		$("#passwordForm").on("submit", function(e){
			e.preventDefault();
			let password = $("#passwordInput").val();
			if(password == ""){
				alert("패스워드를 입력하세요.");
				return;
			}
			$.ajax({
				url: "/account/auth"
				, type: "POST"
				, data: {"password":password}
				, success:function(data){
					if(data.result == "success"){
						location.href = "/account/config-page";
					}else{
						alert("패스워드가 일치하지 않습니다.");
					}
				}
				, error:function(){
					alert("인증 에러");
				}
			});
		});
	});
</script>
</body>
</html>