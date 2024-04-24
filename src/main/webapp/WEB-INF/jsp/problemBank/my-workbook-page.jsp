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
					<h2>나의 문제집 모음</h2>
					<jsp:include page="/WEB-INF/jsp/problemBank/problem-bank-menu.jsp" />
					<table style="width:700px;" class="table my-3 text-center">
						<thead>
							<tr>
								<th width="10%">번호</th>
								<th width="40%">제목</th>
								<th width="10%">문제 수</th>
								<th width="20%">제작자</th>
								<th width="10%">내 점수</th>
								<th width="10%"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="workbook" items="${workbookInfoList }">
								<tr>
									<td>${workbook.id }</td>
									<td><a href="/problem-bank/workbook-test-page?workbookId=${workbook.id }">${workbook.title }</a></td>
									<td>${workbook.problemCount }</td>
									<td>${workbook.userNickname }</td>
									<td>${workbook.myScore }/${workbook.problemCount }</td>
									<td>
										<button value="${workbook.id }" type="button" class="btn btn-sm btn-danger btn-delete">
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
								<c:forEach var="i" begin="1" end="2" step="1">
									<li class="page-item">
										<a class="page-link" href="#">${i } </a>
									</li>
								</c:forEach>
							</ul>
						</nav>
					</div>
					<div class="d-flex w-100 justify-content-end">
						<button onClick="location.href = '/problem-bank/create-workbook-page';" type="button" class="btn btn-primary">문제집 추가</button>
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
			if(!confirm("정말 삭제하시겠습니까?")){
				return;
			}
			let workbookId = $(this).val();
			$.ajax({
				url: "/problem-bank/remove-workbook"
				, type: "DELETE"
				, data: {"workbookId":workbookId}
				, success:function(data){
					if(data.result == "success"){
						alert("삭제되었습니다.");
						location.reload();
					}else if(data.result == "permission denied"){
						alert("권한이 없습니다.");
					}else{
						alert("삭제 실패");
					}
				}
				, error:function(){
					alert("삭제 에러");
				}
			});
		});
	});
</script>

</body>
</html>