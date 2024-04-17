<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
						<c:if test="${ownerDTO.id eq userId }">
							<div class="h2 text-white">메모장</div>
							<ul id="sortable" style="width:100%;">
								<c:forEach var="memo" items="${blogMemoList }">
									<li data-id="${memo.id }" class="ui-state-default">
										<span class="ui-icon ui-icon-arrowthick-2-n-s"></span>${memo.content }
										<button data-id="${memo.id }" type="button" class="btn btn-sm btn-danger delete-memo-btn" style="font-size:10px;">
											<i class="bi bi-eraser-fill"></i>
										</button>
									</li>
								</c:forEach>
							</ul>
							<form id="memoForm" class="d-flex w-100">
								<textarea id="memoInput" type="text" class="form-control" placeholder="새 메모 작성" style="resize:none; width:230px;"></textarea>
								<button type="submit" class="btn btn-sm btn-primary">
									<i class="bi bi-pencil-fill"></i>
								</button>
							</form>
						</c:if>