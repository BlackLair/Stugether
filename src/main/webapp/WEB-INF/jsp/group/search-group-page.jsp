<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹 검색</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
		<section class="p-3 bg-dark">
			<div class="d-flex justify-content-between p-3">
				<div id="myGroupDiv" class="bg-light w-50 d-flex flex-column align-items-center">
					<h2 class="my-3">내 그룹 바로가기</h2>
					<div class="d-flex flex-column w-75 bg-gray p-3" style="min-Height:300px;">
						<div class="w-100 my-2">
							<div class="d-flex justify-content-between align-items-center">
								<a href="#">알고리즘 스터디</a>
								<div class="d-flex align-items-center">
									<span class="mx-3">23명</span>
									<button type="button" class="btn btn-danger">탈퇴</button>
								</div>
							</div>
						</div>
						<div class="w-100 my-2">
							<div class="d-flex justify-content-between align-items-center">
								<a href="#">운영체제 스터디</a>
								<div class="d-flex align-items-center">
									<span class="mx-3">7명</span>
									<button type="button" class="btn btn-danger">탈퇴</button>
								</div>
							</div>
						</div>
					</div>
					<div class="d-flex w-75 justify-content-end">
						<button onClick="location.href = '/group/create-group-page';" type="button" class="btn btn-primary my-3">그룹 생성</button>
					</div>
				</div>
				<div style="width:1px;" class="bg-dark"></div>
				<div id="searchGroupDiv" class="bg-light w-50 d-flex flex-column align-items-center">
					<h2 class="my-3">그룹 검색</h2>
					<div class="d-flex align-items-center justify-content-center w-75">
						<input type="text" style="width:220px;" class="form-control">
						<button type="submit" class="btn btn-primary">검색</button>
					</div>
					<div class="my-3">
						<table class="table">
							<thead>
								<tr>
									<th>그룹명</th>
									<th>멤버 수</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<a href="#">자바 스프링 입문 스터디 그룹</a>
									</td>
									<td>337명</td>
									<td>
										<button type="button" class="btn btn-sm btn-primary">가입</button>
									</td>
								</tr>
								<tr>
									<td>
										<a href="#">네트워크 엔지니어 모여라</a>
									</td>
									<td>47명</td>
									<td>
										<button type="button" class="btn btn-sm btn-primary">가입</button>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="page-div d-flex w-100 justify-content-center">
							<nav class="d-flex justify-content-center">
								<ul class="pagination pagination-sm">
									<c:forEach var="i" begin="1" end="${pageCount }" step="1">
										<li class="page-item">
											<a class="page-link" href="/group/search-workbook-page?page=${i }">${i } </a>
										</li>
									</c:forEach>
								</ul>
							</nav>
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


<script>

</script>

</body>
</html>