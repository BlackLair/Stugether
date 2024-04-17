<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ownerDTO.nickname }의 블로그</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
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
		<section class="p-3 bg-dark">
			<div class="sub-header bg-info d-flex align-items-center justify-content-center">
				<span class="h2">${ownerDTO.nickname }의 블로그</span>
			</div>
			<div class="d-flex justify-content-between my-3">
				<jsp:include page="/WEB-INF/jsp/blog/blog-category.jsp" />
				<main class="p-3">
					<div class="h2">${categoryName }</div>
					<table class="table text-center">
						<thead>
							<tr>
								<th>No.</th>
								<th>글 제목</th>
								<th>업로드</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="post" items="${postList }">
								<tr>
									<td>${post.id }</td>
									<td><a href="/blog/post-detail-page?postId=${post.id }">${post.title }</a></td>
									<td><fmt:formatDate value="${post.createdAt }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="page-div d-flex w-100 justify-content-center">
						<nav class="d-flex justify-content-center">
							<ul class="pagination pagination-sm">
								<c:forEach var="i" begin="1" end="${categoryDTO.currentPages }" step="1">
									<li class="page-item">
										<a class="page-link" href="/blog/list-page?userId=${ownerDTO.id }&category=${categoryDTO.currentCategoryId}&page=${i}">${i } </a>
									</li>
								</c:forEach>
							</ul>
						</nav>
					</div>
				</main>
				<div>
					<div class="widget-box bg-secondary">
						<jsp:include page="/WEB-INF/jsp/blog/blog-memo.jsp" />
					</div>
				</div>
			</div>
		</section>
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js" integrity="sha256-lSjKY0/srUM9BE3dPm+c4fBo1dky2v27Gdjm2uoZaL0=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="/static/js/blog/blogCategoryEdit.js"></script>
<script src="/static/js/blog/blogMemo.js"></script>

<script>
	$(document).ready(function(){
		// 카테고리 UI 이벤트 등록
		addCategoryUIEvent();
		addSortableEvent();
		// 메모 삭제 버튼
		$(".delete-memo-btn").on("click", function(){
			let memoId = $(this).data("id");
			$.ajax({
				url: "/blog/memo/remove"
				, type: "DELETE"
				, data: {"memoId":memoId}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					}else{
						alert("메모 삭제 실패");
					}
				}
				, error:function(){
					alert("메모 삭제 오류");
				}
			})
		});
		
		// 메모 작성
		$("#memoForm").on("submit", function(e){
			e.preventDefault();
			let content = $("#memoInput").val();
			if(content == ""){
				alert("메묘 내용을 입력하세요.");
			}
			$.ajax({
				url: "/blog/memo/upload"
				, type: "POST"
				, data: {"content":content}
				, success:function(data){
					if(data.result == "success"){
						location.reload();
					}else{
						alert("메모 작성 실패");
					}
				}
				, error:function(){
					alert("메모 작성 에러");
				}
			});
		});
	});
</script>

</body>
</html>