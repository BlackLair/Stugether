<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
												<button type="button" data-category-id="${category.id }" class="form-control btn-danger mx-2 btn-mini removeCategoryBtn"  >
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
									<button type="button" id="addCategoryBtn" class="btn-success mx-2" style="width:25px; height:25px; font-size:10px;">
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