<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제 은행</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
		<section class="p-3 bg-dark">
			<div class="d-flex justify-content-center">
				<div style="width:800px;" class="bg-white p-3 d-flex flex-column align-items-center">
					<h2>문제 상세보기</h2>
					<jsp:include page="/WEB-INF/jsp/problemBank/problem-bank-menu.jsp" />
					<div class="my-3 p-3 d-flex flex-column" style="width:700px; background-color:#DDDDDD;">
						<div class="d-flex justify-content-between">
							<h2>#${problemDTO.id } : ${problemDTO.title }</h2>
							<div>제작자 : <span data-user-id="${problemDTO.userId }" class="user-nickname">${problemDTO.userNickname }</span><br>유형 : ${problemDTO.type }</div>
						</div>
						<hr>
						<h4>문제</h4>
						<div class="bg-white card card-body">
							${problemDTO.content }
						</div>
						<hr>
						<c:if test="${problemDTO.type eq '객관식' }">
							<h4>보기</h4>
							<div class="bg-white card card-body">
								<c:forEach var="option" items="${problemDTO.choice }" varStatus="status">
									${status.count }. ${option }<br>
								</c:forEach>
							</div>
							<hr>
						</c:if>
						<button type="button" class="btn btn-primary" data-bs-toggle="collapse" data-bs-target="#collapseExample">정답 및 풀이 보기</button>
						<div class="collapse" id="collapseExample">
							<div class="card card-body">
								정답 : ${problemDTO.answer }<br>
								${problemDTO.solution }
							</div>
						</div>
						<hr>
						<button onClick="history.back();" type="button" class="btn btn-dark">뒤로 가기</button>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="/static/js/common/common.js"></script>

<script>

</script>

</body>
</html>