<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹 검색</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
		<section class="p-3 bg-dark">
			<div class="d-flex justify-content-between p-3">
				<div id="myGroupDiv" class="bg-light w-50 d-flex flex-column align-items-center">
					<h2 class="my-3">내 그룹 바로가기</h2>
					<div class="d-flex flex-column w-75 bg-gray p-3" style="min-Height:300px;">
						<c:forEach var="groupInfo" items="${groupInfoList }">
							<div class="w-100 my-2">
								<div class="d-flex justify-content-between align-items-center">
									<a href="/group/${groupInfo.id }/list-page">${groupInfo.groupName }</a>
									<div class="d-flex align-items-center">
										<span class="mx-3">${groupInfo.memberCount }명</span>
										<button type="button" class="btn btn-danger">탈퇴</button>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="d-flex w-75 justify-content-end">
						<button onClick="location.href = '/group/create-group-page';" type="button" class="btn btn-primary my-3">그룹 생성</button>
					</div>
				</div>
				<div style="width:1px;" class="bg-dark"></div>
				<div id="searchGroupDiv" class="bg-light w-50 d-flex flex-column align-items-center">
					<h2 class="my-3">그룹 검색</h2>
					<form id="searchForm" class="w-75">
						<div class="d-flex align-items-center justify-content-center">
							<input id="searchInput" type="text" style="width:220px;" class="form-control" value="${search }">
							<button type="submit" class="btn btn-primary">검색</button>
						</div>
					</form>
					<div class="my-3 w-75">
						<table class="table text-center">
							<thead>
								<tr>
									<th width="60%">그룹명</th>
									<th width="20%">멤버 수</th>
									<th width="20%"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="searchGroupInfo" items="${searchGroupInfoList }">
									<tr>
										<td>
											<a href="#">${searchGroupInfo.groupName }</a>
										</td>
										<td>${searchGroupInfo.memberCount }명</td>
										<td>
											<button value="${searchGroupInfo.id }" type="button" class="join-btn btn btn-sm btn-primary">가입</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="page-div d-flex w-100 justify-content-center">
							<nav class="d-flex justify-content-center">
								<ul class="pagination pagination-sm">
									<c:forEach var="i" begin="1" end="${pageCount }" step="1">
										<li class="page-item">
											<a class="page-link" href="/group/search-group-page?page=${i }&search=${search}">${i } </a>
										</li>
									</c:forEach>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>


<script>
	$(document).ready(function(){
		$(".join-btn").on("click", function(){
			if(confirm("그룹에 가입하시겠습니까?")){
				let groupId = Number($(this).val());
				$.ajax({
					url: "/group/join"
					, type: "POST"
					, data: {"groupId":groupId}
					, success:function(data){
						if(data.result == "success"){
							alert("그룹에 가입되었습니다.");
						}else if(data.result == "joined"){
							alert("이미 가입한 그룹입니다.");
						}else{
							alert("그룹 가입에 실패했습니다.");
						}
					}
					, error:function(){
						alert("그룹 가입 오류");
					}
				});
			}
		});
		$("#searchForm").on("submit", function(e){
			e.preventDefault();
			let search = $("#searchInput").val();
			location.href = "/group/search-group-page?page=1&search=" + search;
		});
	});
</script>

</body>
</html>