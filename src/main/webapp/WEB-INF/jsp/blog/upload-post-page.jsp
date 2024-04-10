<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>누구씨의 블로그</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.20/dist/summernote-bs5.min.css" rel="stylesheet">
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
			<div class="d-flex justify-content-center">
				<main class="bg-light p-3 my-3">
					<div>
						<h2>블로그 작성</h2>
					</div>
					<div class="d-flex justify-content-center my-3 w-100">
						<select class="form-control" style="width:180px;">
							<option>카테고리 선택</option>
							<option>JAVA</option>
							<option>자료 구조</option>
						</select>
						<input type="text" class="form-control" placeholder="제목을 입력하세요.">
					</div>
					<div id="summernote"></div>
					<div class="d-flex justify-content-between my-3">
						<button type="button" class="btn btn-dark">이전으로</button>
						<button type="button" class="btn btn-primary">등록</button>
					</div>
				</main>
			</div>
		</div>
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.20/dist/summernote-bs5.min.js"></script>
<!-- language pack -->
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.20/dist/lang/summernote-ko-KR.min.js"
integrity="sha256-y2bkXLA0VKwUx5hwbBKnaboRThcu7YOFyuYarJbCnoQ=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		var fontList = ['맑은 고딕','굴림','돋움','바탕','궁서','NotoSansKR','Arial','Courier New','Verdana','Tahoma','Times New Roamn'];
		var sizes = ['8', '9', '10', '11', '12', '15', '19', '24', '36'];
		$("#summernote").summernote({
			placeholder: '내용을 입력하세요.'
			, tabsize: 2
			, height: 400
			, lang: 'ko-KR'
			, fontNames: fontList
			, fontNamesIgnoreCheck: fontList
		});
		$(".dropdown-toggle").dropdown();
	});
</script>	
</body>
</html>