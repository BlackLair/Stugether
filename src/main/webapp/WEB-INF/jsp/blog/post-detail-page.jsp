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
				<aside>
					<div class="category-box bg-secondary d-flex flex-column justify-content-between">
						<div class="p-1">
							<ul class="nav my-2 d-inline">
								<li class="my-1">
									<a class="nav-link nav-link-style" href="/blog/list-page?userId=${ownerDTO.id }">전체 글 (${categoryDTO.allPostCount })</a>
								</li>
								<c:forEach var="category" items="${categoryDTO.blogCategoryDetailList }">
									<li class="my-1">
										<div class="d-flex align-items-center">
											<a class="nav-link nav-link-style" href="/blog/list-page?userId=${ownerDTO.id }&category=${category.id}">${category.name } (${category.postCount })</a>
											<c:if test="${userId eq ownerDTO.id }">
												<button type="button" data-category-id="${category.id }" class="btn-danger removeCategoryBtn edit-category" style="width:25px; height:25px; font-size:10px;" >
													<i class="bi bi-folder-minus"></i>
												</button>
											</c:if>
										</div>
									</li>
								</c:forEach>
							</ul>
							<c:if test="${userId eq ownerDTO.id }">
								<div class="d-flex align-items-center mx-3 my-3 edit-category">
									<div class="col-8"><input id="newCategoryInput" type="text" class="form-control"></div>
									<button type="button" id="addCategoryBtn" class="btn-success mx-2" style="width:25px; height:25px; font-size:10px;" font-size:10px;">
										<i class="bi bi-folder-plus"></i>
									</button>		
								</div>
							</c:if>
						</div>
						<c:if test="${userId eq ownerDTO.id }">
							<div class="d-flex justify-content-start">
									<button onClick="location.href = '/blog/upload-page';" type="button" class="btn btn-sm btn-primary">글쓰기</button>
							</div>
						</c:if>
					</div>
				</aside>
				<main class="p-3">
					<div class="d-flex justify-content-between align-items-start w-100">
						<div>
							<div>${blogPost.categoryName }</div>
							<h2>${blogPost.title }</h2>
						</div>
						<div style="width:180px; border-left:1px solid black;">
							<div class="mx-2">
								<div><fmt:formatDate value="${blogPost.createdAt }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
								<div>글 번호 : ${blogPost.id }</div>
								<div class="d-flex">
									<c:if test="${userId eq ownerDTO.id }">
										<button type="button">수정</button>
										<button id="deleteBtn" type="button">삭제</button>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<hr>
					<div style="overflow-y:auto;" class="article w-100">
						${blogPost.content }
					</div>
					<hr>
					<div class="w-100 bg-dark text-light">
						<div>댓글</div>
						<hr class="bg-white">
						<div class="reply-div">
							<div class="d-flex justify-content-between">
								<div class="d-flex">
									<div><b>나는사람</b> : </div>
									<div>댓글 내용</div>
								</div>
								<!-- 삭제 버튼 여기에 추가하기 -->
							</div>
							<hr class="bg-white m-1">
							<div class="d-flex">
								<input type="text" class="form-control reply-input">
								<button type="button" class="form-control reply-btn">등록</button>
							</div>
						</div>
					</div>
				</main>
				<div>
					<div class="widget-box bg-danger">
					
					</div>
				</div>
			</div>
		</section>
	</div>
	<input id="postData" type="text" data-user-id="${userId }" data-post-id="${blogPost.id }" data-category-id="${blogPost.blogCategoryId }" hidden="true" >
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="/static/js/blog/blogCategoryEdit.js"></script>

<script>
	$(document).ready(function(){
		addCategoryUIEvent();
		$("#deleteBtn").on("click", function(){
			let id = Number($("#postData").data("post-id"));
			let userId = Number($("#postData").data("user-id"));
			let categoryId = Number($("#postData").data("category-id"));
			if(confirm("정말 삭제하시겠습니까?")){
				$.ajax({
					url: "/blog/remove-post"
					, type: "DELETE"
					, data:{"postId": id}
					, success:function(data){
						if(data.result == "success"){
							alert("삭제되었습니다.");
							location.href = "/blog/list-page?category=" + categoryId + "&userId=" + userId;
						}
					}
				});
			}
		});
	});
</script>

</body>
</html>