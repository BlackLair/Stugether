<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<main class="bg-light p-3 my-3">
					<div>
						<h2>문제집 생성</h2>
					</div>
					<div>
						<input type="text" class="form-control" placeholder="문제집 제목을 입력하세요.">
					</div>
					<table class="table text-center">
						<thead>
							<tr>
								<th width="10%">번호</th>
								<th>제목</th>
								<th width="10%">유형</th>
								<th width="10%"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>2</td>
								<td>피이보나치 수열</td>
								<td>주관식</td>
								<td>
									<button value="문제아이디" type="button" class="btn btn-sm btn-danger btn-delete">
										<i class="bi bi-trash3-fill"></i>
									</button>
								</td>
								<tr>
								<td>4</td>
								<td>짱쉬운그림문제</td>
								<td>객관식</td>
								<td>
									<button value="문제아이디" type="button" class="btn btn-sm btn-danger btn-delete">
										<i class="bi bi-trash3-fill"></i>
									</button>
								</td>
							</tr>
							</tr>
						</tbody>
					</table>
				</main>
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

<script>
	$(document).ready(function(){

	});
</script>
</body>
</html>