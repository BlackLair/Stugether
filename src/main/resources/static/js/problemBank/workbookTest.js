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
							location.href = "/problem-bank/workbook-result-page?scoreId=" + data.scoreId;
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