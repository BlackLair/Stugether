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
					<h2>문제집 검색</h2>
					<jsp:include page="/WEB-INF/jsp/problemBank/problem-bank-menu.jsp" />
					<form id="searchForm">
						<div class="d-flex justify-content-center" style="width:500px;">
							<input id="prevType" data-type="${type }" hidden="true">
							<select id="searchTypeSelect" style="width:80px;" class="form-control">
								<option value="workbookId">번호</option>
								<option value="title">이름</option>
								<option value="nickname">닉네임</option>
							</select>
							<input id="searchInput" type="text" style="width:250px;" class="form-control" value="${search }">
							<button type="submit" class="btn btn-primary">검색</button>
						</div>
					</form>
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
									<td><span data-user-id="${workbook.userId }" class="user-nickname">${workbook.userNickname }</span></td>
									<td>${workbook.myScore }/${workbook.problemCount }</td>
									<td>
										<button value="${workbook.id }" type="button" class="btn-favorite btn btn-sm btn-warning">
											<c:choose>
												<c:when test="${workbook.liked }">
													<i class="bi bi-star-fill"></i>
												</c:when>
												<c:otherwise>
													<i class="bi bi-star"></i>
												</c:otherwise>
											</c:choose>
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
										<a class="page-link" href="/problem-bank/search-workbook-page?page=${i }">${i } </a>
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
<script src="/static/js/problemBank/searchWorkbook.js"></script>
<script src="/static/js/common/common.js"></script>
</body>
</html>