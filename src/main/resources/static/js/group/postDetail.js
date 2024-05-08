	$(document).ready(function(){
		addCategoryUIEvent();
		// 게시물 삭제
		$("#deleteBtn").on("click", function(){
			let groupId = Number($("#wrap").data("group-id"));
			let postId = Number($("#groupPostDiv").data("post-id"));
			if(confirm("글을 삭제하시겠습니까?")){
				$.ajax({
					url: "/group/remove-post"
					, type: "DELETE"
					, data: {
						"postId":postId
						, "groupId":groupId
					}
					, success:function(data){
						if(data.result == "success"){
							alert("삭제되었습니다.");
							location.href = "/group/" + groupId;
						}
					}
				});
			}
		});
		
		// 댓글 삭제
		$(".delete-reply-btn").on("click", function(){
			let replyId = Number($(this).val());
			if(confirm("댓글을 삭제하시겠습니까?")){
				$.ajax({
					url: "/group/reply/remove"
					, type: "DELETE"
					, data:{"replyId":replyId}
					, success:function(data){
						if(data.result != "success"){
							alert("삭제 실패");
						}
						location.reload();
					}
					, error:function(){
						alert("삭제 에러");
					}
				});
			}
				
		});
		
		// 댓글 등록
		$("#replyForm").on("submit", function(e){
			e.preventDefault();
			let content = $("#replyInput").val();
			let postId = Number($("#groupPostDiv").data("post-id"));
			let groupId = Number($("#wrap").data("group-id"));
			if(content == ""){
				alert("댓글 내용을 입력하세요.");
				return;
			}
			$.ajax({
				url: "/group/reply/upload"
				, type: "POST"
				, data: {"postId":postId
						, "content":content
						, "groupId":groupId}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					}else if(data.result == "not exist"){
						alert("존재하지 않는 게시물입니다.");
						let userId = $("#postData").data("user-id");
						location.href = "/group/" + groupId;
					}else{
						alert("댓글 작성 실패");
					}
				}
				, error:function(data){
					alert("댓글 작성 오류");
				}
			});
		});
	});