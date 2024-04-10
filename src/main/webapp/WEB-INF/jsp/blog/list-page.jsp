<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>누구씨의 블로그</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
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
						<div class="p-1">
							<ul class="nav my-2">
								<li class="my-1">
									<a class="nav-link nav-link-style d-inline" href="#">전체 글 (12)</a>
								</li>
								<li class="my-1">
									<a class="nav-link nav-link-style d-inline" href="#">JAVA (9)</a>
									<button type="button" class="btn-danger" style="width:25px; height:25px; font-size:10px;" >
										<i class="bi bi-folder-minus"></i>
									</button>
								</li>
								<li class="my-1">
									<a class="nav-link nav-link-style d-inline" href="#">자료구조 (3)</a>
									<button type="button" class="btn-danger" style="width:25px; height:25px; font-size:10px;" >
										<i class="bi bi-folder-minus"></i>
									</button>
								</li>
							</ul>
							<div class="d-flex align-items-center mx-3 my-3">
								<div class="col-8"><input type="text" class="form-control"></div>
								<button type="button" class="btn-success mx-2" style="width:25px; height:25px; font-size:10px;" font-size:10px;">
									<i class="bi bi-folder-plus"></i>
								</button>		
							</div>
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