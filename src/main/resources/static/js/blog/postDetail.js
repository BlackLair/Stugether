	$(document).ready(function(){
		// 카테고리 편집 UI 이벤트 등록
		addCategoryUIEvent();
		addSortableEvent();
		$(".delete-btn").on("click", function(){
			if(!confirm("댓글을 삭제하시겠습니까?")){
				return;
			}
			let replyId = $(this).val();
			$.ajax({
				url: "/blog/reply/remove"
				, type: "DELETE"
				, data:{"replyId":replyId}
				, success:function(data){
					if(data.result != "success"){
						alert("댓글 삭제에 실패했습니다.");
					}
					location.reload();
				}
				, error:function(){
					alert("댓글 삭제 에러");
				}
			});
		});
		
		// 댓글 등록
		$("#replyForm").on("submit", function(e){
			e.preventDefault();
			let content = $("#replyInput").val();
			let postId = Number($("#postData").data("post-id"));
			if(content == ""){
				alert("댓글 내용을 입력하세요.");
				return;
			}
			$.ajax({
				url: "/blog/reply/upload"
				, type: "POST"
				, data: {"postId":postId
						, "content":content}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					}else if(data.result == "not exist"){
						alert("존재하지 않는 게시물입니다.");
						let userId = $("#postData").data("user-id");
						location.href = "/blog/list-page?userId=" + Number(userId);
					}else{
						alert("댓글 작성 실패");
					}
				}
				, error:function(data){
					alert("댓글 작성 오류");
				}
			});
		});
		
		// 게시물 삭제 버튼
		$("#deleteBtn").on("click", function(){
			let postId = Number($("#postData").data("post-id"));
			let categoryId = Number($("#postData").data("category-id"));
			if(confirm("정말 삭제하시겠습니까?")){
				$.ajax({
					url: "/blog/remove-post"
					, type: "DELETE"
					, data:{"postId": postId}
					, success:function(data){
						if(data.result == "success"){
							alert("삭제되었습니다.");
							let userId = $("#postData").data("user-id");
							location.href = "/blog/list-page?category=" + categoryId + "&userId=" + userId;
						}
					}
				});
			}
		});
	});