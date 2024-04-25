<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
						<div id="workbookTitle" data-id="${workbookTestInfo.id }" class="h2">${workbookTestInfo.title }</div>
						<div style="width:200px;">
							<div id="problemCount" data-problem-count="${workbookTestInfo.problemCount }">문항 수 : ${workbookTestInfo.problemCount }</div>
							<div>제작자 : ${workbookTestInfo.userNickname }</div>
						</div>
					</div>
					<c:forEach var="problem" items="${workbookTestInfo.problemDTOList }" varStatus="problemStatus">
						<input data-problem-index="${problemStatus.index }" data-problem-type="${problem.type }" hidden="true">
						<c:choose>
							<c:when test="${problem.type eq '객관식' }"> <!-- 객관식 -->
								<div class="my-3 p-3 d-flex flex-column" style="width:700px; background-color:#DDDDDD;">
									<div class="card card-body">
										<span class="fw-bold">${problemStatus.count }. ${problem.title }</span>
										<hr>
										${problem.content }
									</div>
									<div class="card card-body">
										<c:forEach var="choice" items="${problem.choice }" varStatus="choiceStatus">
											<label><input data-answer="${choiceStatus.count }" name="${problemStatus.index }" type="radio" class="choiceAnswerInput mx-1">${choiceStatus.count }. ${choice }</label>
										</c:forEach>
									</div>
								</div>
							</c:when>
							<c:otherwise> <!--  주관식 -->
								<div class="my-3 p-3 d-flex flex-column" style="width:700px; background-color:#DDDDDD;">
									<div class="card card-body">
										<span class="fw-bold">${problemStatus.count }. ${problem.title }</span>
										<hr>
										${problem.content }
									</div>
									<div class="card card-body">
										<input name="${problemStatus.index }" type="text" class="subjectiveAnswerInput form-control" placeholder="정답을 입력하세요.">
									</div>
								</div>							
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<div class="d-flex justify-content-between align-items-center" style="width:700px;">
						<button onClick="history.back();" type="button" class="btn btn-dark">뒤로가기</button>
						<button id="submitBtn" type="button" class="btn btn-primary">제출</button>
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
	
	$(document).ready(function(){
		let problemCount = Number($("#problemCount").data("problem-count"));
		let workbookId = Number($("#workbookTitle").data("id"));
		let answer = [];
		for(let i = 0; i < problemCount; i++){
			answer[i] = null;
		}
		$("#submitBtn").on("click", function(){
			for(let i = 0; i < problemCount; i++){
				if(answer[i] == null){
					alert("아직 풀지 않은 문제가 있습니다.");
					return;
				}
			}
			if(confirm("정말 제출하시겠습니까?")){
				$.ajax({
					url: "/problem-bank/submit-answer"
					, type: "POST"
					, data:{
						"workbookId":workbookId
						, "answer":answer
					}
					, success:function(data){
						if(data.result == "success"){
							alert("제출 성공 : " + data.scoreId);
						}else{
							alert("제출 실패");
						}
					}
					, error:function(){
						alert("제출 에러");
					}
				});
			}
		});
		
		$(".choiceAnswerInput").on("change", function(){
			let problemNumber = Number($(this).attr("name"));
			answer[problemNumber] = $(this).data("answer");
			console.log(answer);
		});
		$(".subjectiveAnswerInput").on("input", function(){
			let problemNumber = Number($(this).attr("name"));
			let str = $(this).val();
			if(str == ""){
				answer[problemNumber] = null;
			}else{
				answer[problemNumber] = str;
			}
		});
	});
</script>

</body>
</html>