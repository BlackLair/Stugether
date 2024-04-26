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
					<h2>나의 문제 모음</h2>
					<jsp:include page="/WEB-INF/jsp/problemBank/problem-bank-menu.jsp" />
					<table style="width:700px;" class="table my-3 text-center">
						<thead>
							<tr>
								<th width="10%">번호</th>
								<th width="50%">제목</th>
								<th width="10%">유형</th>
								<th width="20%">제작자</th>
								<th width="10%"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="problem" items="${problemInfoDTOList }">
							<tr>
								<td>${problem.id }</td>
								<td><a href="/problem-bank/problem-detail-page?problemId=${problem.id }">${problem.title }</a></td>
								<td>${problem.type }</td>
								<td>${problem.userNickname }</td>
								<td>
									<button value="${problem.id }" type="button" class="btn btn-sm btn-danger btn-delete">
										<i class="bi bi-trash3-fill"></i>
									</button>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="page-div d-flex w-100 justify-content-center">
						<nav class="d-flex justify-content-center">
							<ul class="pagination pagination-sm">
								<c:forEach var="i" begin="1" end="${pageCount }" step="1">
									<li class="page-item">
										<a class="page-link" href="/problem-bank/my-problem-page?page=${i }">${i } </a>
									</li>
								</c:forEach>
							</ul>
						</nav>
					</div>
					<div class="d-flex w-100 justify-content-end">
						<button onClick="location.href = '/problem-bank/create-problem-page';" type="button" class="btn btn-primary">문제 추가</button>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>


<script>
	$(document).ready(function(){
		$(".btn-delete").on("click", function(){
			let problemId = Number($(this).val());
			if(confirm("정말 삭제하시겠습니까?")){
				$.ajax({
					url: "/problem-bank/remove-problem"
					, type: "DELETE"
					, data: {"problemId": problemId}
					, success:function(data){
						if(data.result == "success"){
							alert("삭제되었습니다.");
						}else if(data.result == "not exist"){
							alert("존재하지 않는 문제입니다.");
							
						}else if(data.result == "permission denied"){
							alert("삭제할 권한이 없습니다.");
						}else{
							alert("문제 삭제 실패");
						}
						location.reload();
					}
					, error:function(data){
						alert("문제 삭제 에러");
					}
				});
			}
		});
	});
</script>

</body>
</html>