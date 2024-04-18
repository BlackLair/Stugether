<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 문제</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
		<section class="p-3 bg-dark text-white">
			<div class="d-flex justify-content-center">
				<div style="width:800px;" class="bg-white p-3 d-flex flex-column align-items-center">
					<div style="width:700px;">
						<nav class="bg-secondary d-flex justify-content-around align-items-center">
							<ul class="nav nav-fill w-100">
								<li class="nav-item"><a class="nav-link nav-link-style" href="#"><b>나의 문제</b></a></li>
								<li class="nav-item"><a class="nav-link nav-link-style" href="#">나의 문제집</a></li>
								<li class="nav-item"><a class="nav-link nav-link-style" href="#">즐겨찾기한 문제집</a></li>
								<li class="nav-item"><a class="nav-link nav-link-style" href="#">문제집 검색</a></li>
							</ul>
						</nav>
					</div>
					<table class="table my-3">
						<thead>
							<tr>
								<th>문제 번호</th>
								<th>문제 제목</th>
								<th>유형</th>
								<th>제작자</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td><a href="#">피보나치 수열</a></td>
								<td>주관식</td>
								<td>제작자명</td>
								<td>
									<button type="button" class="btn btn-sm btn-danger">삭제</button>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td><a href="#">고속 푸리에 변환</a></td>
								<td>주관식</td>
								<td>제작자명</td>
								<td>
									<button type="button" class="btn btn-sm btn-danger">삭제</button>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="page-div d-flex w-100 justify-content-center">
						<nav class="d-flex justify-content-center">
							<ul class="pagination pagination-sm">
								<li class="page-item">
									<a class="page-link" href="#">1</a>
								</li>
							</ul>
						</nav>
					</div>
					<div class="d-flex w-100 justify-content-end">
						<button type="button" class="btn btn-primary">문제 추가</button>
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