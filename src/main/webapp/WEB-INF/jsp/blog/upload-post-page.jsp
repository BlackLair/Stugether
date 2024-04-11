<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>누구씨의 블로그</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<nav class="bg-secondary d-flex justify-content-around align-items-center">
			<ul class="nav nav-fill w-100">
				<li class="nav-item"><a class="nav-link nav-link-style" href="#">블로그</a></li>
				<li class="nav-item"><a class="nav-link nav-link-style" href="#">그룹</a></li>
				<li class="nav-item"><a class="nav-link nav-link-style" href="#">문제 은행</a></li>
				<li class="nav-item"><a class="nav-link nav-link-style" href="#">질문과 답변</a></li>
				<li class="nav-item"><a class="nav-link nav-link-style" href="#">계정 관리</a></li>
			</ul>
		</nav>
		<div class="p-3 bg-dark">
			<div class="sub-header bg-info d-flex align-items-center justify-content-center">
				<span class="h2">누구누구의 블로그</span>
			</div>
			<div class="d-flex justify-content-center">
				<main class="bg-light p-3 my-3">
					<div>
						<h2>블로그 작성</h2>
					</div>
					<div class="d-flex justify-content-center my-3 w-100">
						<select class="form-control" style="width:180px;">
							<option>카테고리 선택</option>
							<option>JAVA</option>
							<option>자료 구조</option>
						</select>
						<input type="text" class="form-control" placeholder="제목을 입력하세요.">
					</div>
					<div id="summernote"></div>
					<div class="d-flex justify-content-between my-3">
						<button type="button" class="btn btn-dark">이전으로</button>
						<button type="button" class="btn btn-primary">등록</button>
					</div>
				</main>
			</div>
		</div>
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<!-- language pack -->
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/lang/summernote-ko-KR.min.js"></script>
<script>
	$(document).ready(function(){
		function uploadSummernoteImageFile(file, el, caption){ // 임시 이미지 파일 업로드
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data: data
				, type: "POST"
				, url: "/blog/upload-image"
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
		
		function deleteSummernoteImageFile(imageName){ // 임시 이미지 파일 삭제
			data = new FormData();
			data.append("file", imageName);
			alert("imageName : " + imageName);
			$.ajax({
				data: data
				, type: "DELETE"
				, url: "/blog/delete-image"
				, contentType: false
				, enctype: "multipart/form-data"
				, processData: false
			});
		}
		
		var fontList = ['맑은 고딕','굴림','돋움','바탕','궁서','NotoSansKR','Arial','Courier New','Verdana','Tahoma','Times New Roamn'];
		var sizes = ['8', '9', '10', '11', '12', '15', '19', '24', '36'];
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
						uploadSummernoteImageFile(files[i], this, caption); // 임시 이미지 업로드
					}
				}
				, onMediaDelete: function($target, editor, $editable){ // 글 작성 중 이미지 삭제 시
					if(confirm("이미지를 삭제하시겠습니까?")){
						let deletedImageUrl = $target
							.attr('src')
							.split('/')
							.pop();
						deleteSummernoteImageFile(deletedImageUrl);
					}
				}
			}
		});
		$(".dropdown-toggle").dropdown();
	});
</script>	
</body>
</html>