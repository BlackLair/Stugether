$(document).ready(function(){
	let editorToken = getToken();
	let postType = "blog"; // blog/group/problem/question
	
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
			}
			, success:function(data){
				alert(data.result);
			}
			, error:function(){
				alert("게시물 업로드 에러");
			}
			
		});
	});
	let fontList = ['맑은 고딕','굴림','돋움','바탕','궁서','NotoSansKR','Arial','Courier New','Verdana','Tahoma','Times New Roamn'];
	let sizes = ['8', '9', '10', '11', '12', '15', '19', '24', '36'];
	$("#summernote").summernote({
		placeholder: '내용을 입력하세요.'
		, tabsize: 2
		, height: 400
		, lang: 'ko-KR'
		, fontNames: fontList
		, fontNamesIgnoreCheck: fontList
		, callbacks:{
			onImageUpload: function(files, editor, welEditable){ // 이미지 삽입 콜백
				for(let i = files.length - 1; i >= 0; i--){ // 여러 이미지 한 번에 삽입할 수 있으므로 반복문 사용
					let fileName = files[i].name;
					
					let caption = prompt("이미지 설명 :", fileName);
					if(caption == ""){
						caption = "이미지";
					}
					uploadSummernoteImageFile(files[i], this, caption, postType, editorToken); // 임시 이미지 업로드
				}
			}
			, onMediaDelete: function($target, editor, $editable){ // 글 작성 중 이미지 삭제 시
				if(confirm("이미지를 삭제하시겠습니까?")){
					let deletedImageUrl = $target
						.attr('src')
						.split('/')
						.pop();
					deleteSummernoteImageFile(deletedImageUrl, postType, editorToken);
				}
			}
		}
	});
	$(".dropdown-toggle").dropdown();
});