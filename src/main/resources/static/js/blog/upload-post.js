$(document).ready(function(){
	let editorToken = getToken();
	let postType = "blog"; // blog/group/problem/question
	setUpSummerNote(editorToken);
	$("#uploadBtn").on("click", function(){
		let category = $("#categorySelect").val();
		let title = $("#titleInput").val();
		let content = $("#summernote").summernote('code');
		if(category == ""){
			alert("카테고리를 선택하세요.");
			return;
		}
		if(title == ""){
			alert("제목을 입력하세요.");
			return;
		}
		if(content == ""){
			alert("내용을 입력하세요.");
		}
		$.ajax({
			url: "/" + postType + "/upload-post"
			, type: "POST"
			, data:{"categoryId" : category
				, "title" : title
				, "content" : content
				, "editorToken" : editorToken
				, "type" : postType
			}
			, success:function(data){
				if(data.result == "success"){
					location.href = "/blog/post-detail-page?postId=" + data.postId;
				}else{
					alert("게시물 업로드 실패");
				}
			}
			, error:function(){
				alert("게시물 업로드 에러");
			}
			
		});
	});
});