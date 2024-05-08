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
					<h2>제출 이력</h2>
					<jsp:include page="/WEB-INF/jsp/problemBank/problem-bank-menu.jsp" />
					<table style="width:700px;" class="table my-3 text-center">
						<thead>
							<tr>
								<th width="10%">번호</th>
								<th width="50%">문제집 이름</th>
								<th width="30%">제출 일시</th>
								<th width="10%">점수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="workbookScore" items="${workbookScoreList }">
								<tr>
									<td>${workbookScore.id }</td>
									<td><a href="/problem-bank/workbook-result-page?scoreId=${workbookScore.id }">${workbookScore.title }</a></td>
									<td>
										<fmt:formatDate value="${workbookScore.createdAt }" pattern="yyyy/MM/dd HH:mm:ss"/>
									</td>
									<td>${workbookScore.score } / ${workbookScore.problemCount }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="page-div d-flex w-100 justify-content-center">
						<nav class="d-flex justify-content-center">
							<ul class="pagination pagination-sm">
								<c:forEach var="i" begin="1" end="${pageCount }" step="1">
									<li class="page-item">
										<a class="page-link" href="/problem-bank/score-history-page?page=${i }">${i } </a>
									</li>
								</c:forEach>
							</ul>
						</nav>
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