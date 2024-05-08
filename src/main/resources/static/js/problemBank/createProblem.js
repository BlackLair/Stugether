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