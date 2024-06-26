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
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
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
						<h2>문제 생성</h2>
					</div>
					<div class="d-flex justify-content-center my-3 w-100">
						<input id="titleInput" type="text" class="form-control" placeholder="문제 제목을 입력하세요.">
					</div>
					<div id="summernote"></div>
					<div class="my-3 d-flex align-items-center">
						<h4>정답 설정</h4>
						<select id="typeSelect" class="form-control text-center mx-3" style="width:100px;">
							<option value="객관식">객관식</option>
							<option value="주관식">주관식</option>
						</select>
					</div>
					<div class="choice-div">
						<div class="choice-card card card-body">
							<div id="addChoiceDiv" class="d-flex my-2">
								<input id="choiceInput" type="text" class="form-control">
								<button id="addChoiceBtn" type="button" class="form-control btn-primary" style="width:120px;">보기 추가</button>
							</div>
						</div>
					</div>
					<div class="none-choice-div d-none">
						<div class="card card-body">
							<input id="answerInput" type="text" class="form-control" placeholder="정답을 입력하세요.">
						</div>
					</div>
					<div class="my-3 d-flex align-items-center">
							<h4>문제 풀이 작성</h4>
						</div>
					<div class="card card-body">
						<textarea id="solutionInput" class="form-control" type="textarea" style="height:100px; resize:none;" placeholder="문제 풀이를 입력하세요."></textarea>
					</div>
					<div class="d-flex justify-content-between my-3">
						<button onClick="history.back();" type="button" class="btn btn-dark">이전으로</button>
						<button id="uploadBtn" type="button" class="btn btn-primary">등록</button>
					</div>
					
				</main>
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<!-- language pack -->
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/lang/summernote-ko-KR.min.js"></script>
<script src="/static/js/common/common.js"></script>
<script src="/static/js/common/editor.js"></script>
<script src="/static/js/problemBank/createProblem.js"></script>
</body>
</html>