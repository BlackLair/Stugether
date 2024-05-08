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
						location.replace("/problem-bank/my-workbook-page");
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