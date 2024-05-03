<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${groupInfo.groupName }</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div data-group-id="${groupInfo.id }" id="wrap">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
		<section class="p-3 bg-dark">
			<div class="sub-header bg-info d-flex align-items-center justify-content-between">
				<span class="h2 mx-4">${groupInfo.groupName }</span>
				<div class="d-flex mx-5">
					<div style="width:400px;" class="mx-5 border">${groupInfo.description }</div>
					<div class="border bg-secondary text-white">
						<div>그룹장 : ${groupInfo.userNickname }</div>
						<div>그룹원 : ${groupInfo.memberCount }명</div>
					</div>
				</div>
			</div>
			<div class="d-flex justify-content-between my-3">
				<jsp:include page="/WEB-INF/jsp/group/group-category.jsp" />
				<main class="p-3">
					
					<div id="groupPostDiv" data-post-id="${groupPostDetail.id }" class="d-flex justify-content-between align-items-start w-100">
						<div style="width:520px;">
							<div><a href="/group/${groupInfo.id }/list-page?category=${groupPostDetail.groupCategoryId}">${groupPostDetail.groupCategoryName }</a></div>
							<h2>${groupPostDetail.title }</h2>
						</div>
						<div style="width:180px; border-left:1px solid black;">
							<div class="mx-2">
								<div>작성자 : ${groupPostDetail.userNickname }</div>
								<div><fmt:formatDate value="${groupPostDetail.createdAt }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
								<div>글 번호 : ${groupPostDetail.id }</div>
								<div class="d-flex">
									<c:if test="${userId eq groupPostDetail.userId }">
										<button type="button">수정</button>
										<button id="deleteBtn" type="button">삭제</button>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<hr>
					<div style="overflow-y:auto;" class="article w-100 card card-body">
						${groupPostDetail.content }
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
											<div><b>${reply.userNickname }</b> : ${reply.content }</div>
										</div>
										<div class="d-flex align-items-center">
											<fmt:formatDate value="${reply.createdAt }" pattern="yyyy-MM-dd HH:mm:ss" />
											<div style="width:60px;" class="mx-2">
												<c:if test="${userId eq reply.userId }">
													<button value="${reply.id }" type="button" class="btn btn-sm btn-danger delete-reply-btn">삭제</button>
												</c:if>
											</div>
										</div>
									</div>
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
				<div class="widget-box">
				</div>
				
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js" integrity="sha256-lSjKY0/srUM9BE3dPm+c4fBo1dky2v27Gdjm2uoZaL0=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="/static/js/group/groupCategoryEdit.js" ></script>
<script>
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
	
</script>

</body>
</html>