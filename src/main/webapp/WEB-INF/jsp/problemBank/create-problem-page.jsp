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

<script src="/static/js/common/editor.js"></script>
<script>
	$(document).ready(function(){
		let editorToken = getToken();
		let postType = "problem"; // blog/group/problem/question
		setUpSummerNote(editorToken);
		
		let choiceList = []; // 입력된 객관식 문제 선택지
		
		$("#uploadBtn").on("click", function(){
			let title = $("#titleInput").val();
			let content = $("#summernote").summernote('code');
			let type = $("#typeSelect").val();
			let solution = $("#solutionInput").val();
			if(title == ""){
				alert("제목을 입력하세요.");
				return;
			}
			if(content == ""){
				alert("내용을 입력하세요.");
			}
			if(solution == ""){
				alert("풀이를 입력하세요.");
				return;
			}
			
			let choiceList = null; // 객관식 문제 보기 배열
			let answer = "-1"; // 정답
			if(type == "객관식"){
				choiceList = [];
				$("input:radio[name=choice]").each(function(i, elements){
					if($(this).is(":checked")){
						answer = "" + (i + 1); // 실제 정답 번호는 인덱스 + 1
					}
					choiceList.push("" + $(this).data("choice"));
				});
				if(answer == "-1"){
					alert("정답을 선택하세요.");
					return;
				}
			}else{
				answer = $("#answerInput").val();
				if(answer == ""){
					alert("정답을 입력하세요.");
					return;
				}
			}
			$.ajax({
				url: "/problem-bank/create-problem"
				, type: "POST"
				, data: {
					"title":title
					, "content":content
					, "answer":answer
					, "choice":choiceList
					, "solution":solution
					, "editorToken":editorToken
				}
				, success:function(data){
					if(data.result == "success"){
						let problemId = data.problemId;
						location.replace("/problem-bank/problem-detail-page?problemId=" + problemId);
					}else{
						alert("문제 생성 실패");
					}
				}
				, error:function(){
					alert("문제 생성 오류");
				}
			});

			////////////// 테스트 코드 /////////////
			if(answer >= 0)
				console.log("답:" + choiceList[answer] + "(" + (answer + 1) + "번)");
			console.log(choiceList);
			console.log($("#typeSelect").val());
			/////////////////////////////////////
		});
		
		// 객관식 보기 추가/제거 이벤트
		$("#addChoiceBtn").on("click", function(){
			let text = $("#choiceInput").val();
			if(text == ""){
				alert("보기를 입력하세요.");
				return;
			}
			$("#addChoiceDiv").before(`<div class="d-flex">
					<label><input type="radio" name="choice" data-choice="` + text + `" class="choice-radio mx-2">` + text + `</label>
					<button type="button" class="btn-delete-choice form-control btn-danger mx-2 btn-mini"><i class="bi bi-x"></i></button>
				</div>`);
			$(".btn-delete-choice").each(function(index, item){
				$(this).off("click");
				$(this).on("click", function(){
					let target = $(this).parent();
					target.remove();
				});
			});
			$("#choiceInput").val("");
		});
		
		// 문제 유형 설정 버튼
		$("#typeSelect").on("change", function(){
			let type = $(this).val();
			if(type == "객관식"){
				$(".none-choice-div").addClass("d-none");
				$(".choice-div").removeClass("d-none");
			}else{
				$(".choice-div").addClass("d-none");
				$(".none-choice-div").removeClass("d-none");
			}
		});
	});
</script>
</body>
</html>