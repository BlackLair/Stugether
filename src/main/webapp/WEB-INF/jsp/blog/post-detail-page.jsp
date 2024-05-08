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
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
		<section class="p-3 bg-dark">
			<div class="sub-header bg-info d-flex align-items-center justify-content-center">
				<span class="h2">${ownerDTO.nickname }의 블로그</span>
			</div>
			<div class="d-flex justify-content-between my-3">
				<jsp:include page="/WEB-INF/jsp/blog/blog-category.jsp" />
				<main class="p-3">
					<div class="d-flex justify-content-between align-items-start w-100">
						<div>
							<div><a href="/blog/list-page?userId=${ownerDTO.id }&category=${blogPost.blogCategoryId}">${blogPost.categoryName }</a></div>
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
					<div style="overflow-y:auto;" class="article w-100 card card-body">
						${blogPost.content }
					</div>
					<hr>
					<div class="w-100 bg-dark text-light">
						<div>댓글 ${replyDTOList.size() }</div>
						<hr class="bg-white">
						<div class="reply-div">
							<c:forEach var="reply" items="${replyDTOList }">
								<div class="d-flex justify-content-between">
									<div class="d-flex justify-content-between align-items-center w-100">
										<div class="d-flex">
											<div><span data-user-id="${reply.userId }" class="user-nickname">${reply.userNickname }</span> :${reply.content } </div>
										</div>
										<div class="d-flex align-items-center">
											<fmt:formatDate value="${reply.createdAt }" pattern="yyyy-MM-dd HH:mm:ss" />
											<div style="width:60px;" class="mx-2">
												<c:if test="${userId eq reply.userId }">
													<button value="${reply.id }" type="button" class="btn btn-sm btn-danger delete-btn">삭제</button>
												</c:if>
											</div>
										</div>
									</div>
									<!-- 삭제 버튼 여기에 추가하기 -->
								</div>
								<hr class="bg-white m-1">
							</c:forEach>
							<form id="replyForm">
								<div class="d-flex">
									<input id="replyInput" type="text" class="form-control reply-input" placeholder="댓글을 작성하세요.">
									<button id="replyBtn" type="submit" class="form-control reply-btn">등록</button>
								</div>
							</form>
						</div>
					</div>
				</main>
				<div>
					<div class="widget-box bg-secondary">
						<jsp:include page="/WEB-INF/jsp/blog/blog-memo.jsp" />
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
	<input id="postData" type="text" data-user-id="${userId }" data-post-id="${blogPost.id }" data-category-id="${blogPost.blogCategoryId }" hidden="true" >
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js" integrity="sha256-lSjKY0/srUM9BE3dPm+c4fBo1dky2v27Gdjm2uoZaL0=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="/static/js/common/common.js"></script>
<script src="/static/js/blog/blogCategoryEdit.js"></script>
<script src="/static/js/blog/blogMemo.js"></script>
<script src="/static/js/blog/postDetail.js"></script>

</body>
</html>