<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제집 풀기</title>
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
					<h2>문제집 풀기</h2>
					<jsp:include page="/WEB-INF/jsp/problemBank/problem-bank-menu.jsp" />
					<div style="width:700px;" class="d-flex justify-content-between align-items-center">
						<div class="h2">문제집 이름</div>
						<div style="width:200px;">
							<div>문항 수 : 10</div>
							<div>제작자 : 나는문제제작자</div>
						</div>
					</div>
					<div class="my-3 p-3 d-flex flex-column" style="width:700px; background-color:#DDDDDD;">
						<div class="card card-body">
							1. 다음 중 올바르지 않은 것을 고르면?
						</div>
						<div class="card card-body">
							<label><input name="1" type="radio" class="mx-1">1. 1 + 2 = 3</label>
							<label><input name="1" type="radio" class="mx-1">2. VMware ESXi는 Type 2 하이퍼바이저이다.</label>
						</div>
					</div>
					<div class="my-3 p-3 d-flex flex-column" style="width:700px; background-color:#DDDDDD;">
						<div class="card card-body">
							2. VMware vSphere의 기능으로, 클러스터 내 ESXi들의 성능 사용량에 따라 실행 중인 VM들을 vMotion을 통해 재배치하거나 새로 실행되는 VM의 Host를 결정하여 로드 밸런싱을 지원하는 기능은?(알파벳 3글자)
						</div>
						<div class="card card-body">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="d-flex justify-content-between align-items-center" style="width:700px;">
						<button type="button" class="btn btn-dark">뒤로가기</button>
						<button type="button" class="btn btn-primary">채점하기</button>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>