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

// SummerNote 초기화
function setUpSummerNote(editorToken){
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
					uploadSummernoteImageFile(files[i], this, caption, editorToken); // 임시 이미지 업로드
				}
			}
			, onMediaDelete: function($target, editor, $editable){ // 글 작성 중 이미지 삭제 시
				if(confirm("이미지를 삭제하시겠습니까?")){
					let deletedImageUrl = $target
						.attr('src')
						.split('/')
						.pop();
					deleteSummernoteImageFile(deletedImageUrl, editorToken);
				}
			}
		}
	});
	$(".dropdown-toggle").dropdown();
}
