// 페이지 단위로 게시글 작성 에디터 고유 토큰 값 발급받기
function getToken(){
	let token;
	$.ajax({
		url: "/editor/token"
		, type: "GET"
		, async: false // 동기식으로 바꿔 ajax 요청이 끝나기 전에 함수가 리턴되지 않도록 한다.
		, success:function(data){
			token = data;
		}
		, error:function(){
			alert("에디터 토큰 로드 실패. 새로고침해주세요.");
		}
	});
	return token;
}
// 임시 이미지 파일 업로드
function uploadSummernoteImageFile(file, el, caption, editorToken){ 
	data = new FormData();
	data.append("file", file);
	data.append("editorToken", editorToken);
	$.ajax({
		data: data
		, type: "POST"
		, url: "/editor/upload-image"
		, contentType: false
		, enctype: "multipart/form-data"
		, processData: false
		, success: function(data){
			$(el).summernote(
				"editor.insertImage"
				, data.url
				, function($image){
					$image.attr("alt", caption); // 이미지 alt 속성에 caption을 설정
				}
			)
		}
	});
}
// 임시 이미지 파일 삭제
function deleteSummernoteImageFile(imageName, editorToken){ 
	data = new FormData();
	data.append("file", imageName);
	data.append("editorToken", editorToken);
	$.ajax({
		data: data
		, type: "DELETE"
		, url: "/editor/delete-image"
		, contentType: false
		, enctype: "multipart/form-data"
		, processData: false
	});
}
