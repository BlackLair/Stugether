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
						<input id="titleInput" type="text" class="form-control" placeholder="문제집 제목을 입력하세요.">
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
						<tbody id="problemTbody">
						</tbody>
					</table>
					<div class="d-flex align-items-center">
						<div>문제 추가</div>
						<div class="col-4 mx-3">
							<input id="problemIdInput" type="text" class="form-control" placeholder="문제 번호를 입력하세요.">
						</div>
						<button id="addProblemBtn" type="button" class="btn btn-primary mx-3">문제 추가</button>
						<div class="d-flex">총 문제 수 : <div id="problemCount" class="mx-2">0</div></div>
					</div>
					<div class="d-flex justify-content-between my-3">
						<button onClick="history.back();" type="button" class="btn btn-dark">이전으로</button>
						<button id="addWorkbookBtn" type="button" class="btn btn-primary">문제집 생성</button>
					</div>
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
		let problemCount = 0;
		let problemList = [];
		function setProblemCount(offset){
			problemCount = problemCount + offset;
			$("#problemCount").html(problemCount);
		}
		function setDeleteBtnEvent(){
			$(".btn-delete").off("click");
			$(".btn-delete").on("click", function(){
				let tr = $(this).parent().parent();
				let problemId = Number($(this).val());
				tr.remove();
				problemList.splice(problemList.indexOf(problemId), 1);
				setProblemCount(-1);
			});
		}
		
		$("#addWorkbookBtn").on("click", function(){
			if(problemCount == 0){
				alert("추가된 문제가 없습니다.");
				return;
			}
			let title = $("#titleInput").val();
			if(title == ""){
				alert("문제집 제목을 입력하세요.");
				return;
			}
			$.ajax({
				url: "/problem-bank/create-workbook"
				, type: "POST"
				, data: {"title":title, "problemIdList":problemList}
				, success:function(data){
					if(data.result == "success"){
						alert("문제집이 생성되었습니다.");
						location.href = "/problem-bank/my-workbook-page";
					}else if(data.result == "empty"){
						alert("추가된 문제가 없습니다.");
					}else{
						alert("문제집 생성 실패");
					}
				}
				, error:function(){
					alert("문제집 생성 오류");
				}
			});
		});
		
		$("#addProblemBtn").on("click", function(){
			let problemId = $("#problemIdInput").val();
			$("#problemIdInput").val("");
			if(problemId == ""){
				alert("문제 번호를 입력하세요.");
				return;
			}
			if(isNaN(problemId)){
				alert("숫자만 입력 가능합니다.");
				return;
			}
			problemId = Number(problemId);
			if(problemList.includes(problemId)){
				alert("이미 추가한 문제입니다.");
				return;
			}
			$.ajax({
				url: "/problem-bank/problem-exist"
				, type: "GET"
				, data: {"problemId":problemId}
				, success:function(data){
					if(data.result == "success"){
						$("#problemTbody").append(`
								<tr data-problem-id="` + problemId + `">
								<td>` + problemId + `</td>
								<td>` + data.title + `</td>
								<td>` + data.type + `</td>
								<td>
									<button value="` + problemId + `" type="button" class="btn btn-sm btn-danger btn-delete">
										<i class="bi bi-trash3-fill"></i>
									</button>
								</td>
								</tr>
								`);
						setDeleteBtnEvent();
						setProblemCount(1);
						problemList.push(problemId);
					}else if(data.result == "not exist"){
						alert("존재하지 않는 문제입니다.");
					}
				}
				, error:function(data){
					alert("문제 정보 조회 오류");
				}
			});
		});
	});
</script>
</body>
</html>