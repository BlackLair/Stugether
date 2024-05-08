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
		<section class="p-3 bg-dark d-flex align-items-center justify-content-center">
			<div class="bg-light w-75 my-4 p-3 text-center">
				<h2>그룹 생성</h2>
				<div class="d-flex w-100 my-5">
					<div class="w-75 d-flex flex-column align-items-center">
						<div class="d-flex w-100 justify-content-start align-items-center">
							<div style="width:80px;">그룹명</div>
							<input id="groupNameInput" type="text" style="width:220px;" class="form-control mx-1">
							<button id="checkDuplicatedBtn" type="button" class="btn btn-sm btn-primary">중복 확인</button>
						</div>
						<div class="d-flex w-100 justify-content-start align-items-center my-3">
							<div style="width:80px;">그룹 소개</div>
							<textarea id="descriptionInput" type="text" style="width:220px; height:100px; resize:none;" class="form-control mx-1"></textarea>
							
						</div>
					</div>
					<div class="w-50 d-flex flex-column align-items-center">
						<h5>게시판 카테고리 추가</h5>
						<table class="table">
							<thead>
								<tr>
									<th width="20%">No.</th>
									<th width="80%">카테고리명</th>
									<th>
								</tr>
							</thead>
							<tbody id="category-tbody">
								<tr data-category-num="1">
									<td class="categoryNum">1</td>
									<td>자유게시판</td>
									<td>
									</td>
								</tr>
							</tbody>
						</table>
						<form id="addCategoryForm">
							<div class="d-flex align-items-center justify-content-between my-3">
								<input id="categoryNameInput" type="text" style="width:200px;" class="form-control">
								<button id="addCategoryBtn" type="submit" class="btn btn-primary">추가</button>
							</div>
						</form>
					</div>
				</div>
				<div class="w-100 d-flex justify-content-end">
					<button id="createGroupBtn" type="button" class="btn btn-primary">그룹 생성</button>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>
	
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="/static/js/common/common.js"></script>
<script src="/static/js/group/createGroup.js"></script>

</body>
</html>