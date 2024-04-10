<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>누구씨의 블로그</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<nav class="bg-secondary d-flex justify-content-around align-items-center">
			<ul class="nav nav-fill w-100">
				<li class="nav-item"><a class="nav-link nav-link-style" href="#">블로그</a></li>
				<li class="nav-item"><a class="nav-link nav-link-style" href="#">그룹</a></li>
				<li class="nav-item"><a class="nav-link nav-link-style" href="#">문제 은행</a></li>
				<li class="nav-item"><a class="nav-link nav-link-style" href="#">질문과 답변</a></li>
				<li class="nav-item"><a class="nav-link nav-link-style" href="#">계정 관리</a></li>
			</ul>
		</nav>
		<div class="p-3 bg-dark">
			<div class="sub-header bg-info d-flex align-items-center justify-content-center">
				<span class="h2">누구누구의 블로그</span>
			</div>
			<div class="d-flex justify-content-between my-3">
				<aside>
					<div class="category-box bg-secondary d-flex flex-column justify-content-between">
						<div>
							<ul class="nav">
								<li class="nav-item"><a class="nav-link nav-link-style" href="#">전체 글 (12)</a></li>
								<li class="nav-item"><a class="nav-link nav-link-style" href="#">JAVA (9)</a></li>
								<li class="nav-item"><a class="nav-link nav-link-style" href="#">자료 구조 (3)</a></li>
							</ul>
						</div>
						<div class="d-flex justify-content-between">
								<button type="button" class="btn btn-sm btn-primary">글쓰기</button>
								<button type="button" class="btn btn-sm btn-danger">카테고리 편집</button>
						</div>
					</div>
				</aside>
				<main class="bg-light p-3">
					<div class="h2">전체 글</div>
					<table class="table">
						<thead>
							<tr>
								<th>No.</th>
								<th>글 제목</th>
								<th>업로드</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>재귀함수란 무엇인가?</td>
								<td>2024-04-03 14:12:15</td>
							</tr>
							<tr>
								<td>2</td>
								<td>Collection 프레임워크란?</td>
								<td>2024-04-06 11:41:24</td>
							</tr>
						</tbody>
					</table>
				</main>
				<div>
					<div class="widget-box bg-danger">
					
					</div>
				</div>
			</div>
		</div>
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
		
</body>
</html>