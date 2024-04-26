<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채점 결과</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="/static/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<c:set var="workbookTestInfo" value="${workbookScoreInfo.workbookTestInfo }" />
	<div id="wrap">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp" />
		<section class="p-3 bg-dark">
			<div class="d-flex justify-content-center">
				<div style="width:800px;" class="bg-white p-3 d-flex flex-column align-items-center">
					<h2>채점 결과</h2>
					<jsp:include page="/WEB-INF/jsp/problemBank/problem-bank-menu.jsp" />
					<div style="width:700px;" class="d-flex justify-content-between align-items-center">
						<div class="h2">${workbookTestInfo.title }</div>
						<div style="width:200px;">
							<div>문항 수 : ${workbookTestInfo.problemCount }</div>
							<div>제작자 : ${workbookTestInfo.userNickname }</div>
						</div>
					</div>
					<div class="my-3 p-3 d-flex flex-column" style="width:700px; background-color:#DDDDDD;">
						<div class="card card-body">
							<div class="h5">
								맞힌 문항 수 : ${workbookScoreInfo.score } / ${workbookTestInfo.problemCount } ( ${workbookScoreInfo.score * 100 / workbookTestInfo.problemCount }% )
							</div>
							<table style="table-layout:fixed;" class="table table-bordered border-dark text-center my-3">
								<tbody>
									<c:forEach var="i" begin="0" end="${workbookTestInfo.problemCount / 10}" step="1">
										<tr class="bg-gray">
											<c:forEach var="j" begin="1" end="10" step="1">
											<c:set var="problemNumber" value="${(i * 10) + j }" />
												<td>
													<c:if test="${problemNumber le workbookTestInfo.problemCount }">
														${(i * 10) + j }
													</c:if>
												</td>
											</c:forEach>
										</tr>
										<tr class="fw-bold">
											<c:forEach var="j" begin="1" end="10" step="1">
												<c:set var="problemNumber" value="${(i * 10) + j }" />
												<c:choose>
													<c:when test="${problemNumber le workbookTestInfo.problemCount }">
														<c:choose>
															<c:when test="${workbookScoreInfo.userAnswer[problemNumber - 1] eq workbookTestInfo.problemDTOList.get(problemNumber - 1).answer }">
																<td class="text-success">O</td>
															</c:when>
															<c:when test="${workbookScoreInfo.userAnswer[problemNumber - 1] ne workbookTestInfo.problemDTOList.get(problemNumber - 1).answer }">
																<td class="text-danger">X</td>
															</c:when>
														</c:choose>
													</c:when>
													<c:otherwise>
														<td></td>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<c:forEach var="problem" items="${workbookTestInfo.problemDTOList }" varStatus="problemStatus">
						<c:choose>
							<c:when test="${problem.type eq '객관식' }"> <!-- 객관식 -->
								<div class="my-3 p-3 d-flex flex-column" style="width:700px; background-color:#DDDDDD;">
									<div class="card card-body">
										<span class="fw-bold">${problemStatus.count }. ${problem.title }</span>
										<hr>
										${problem.content }
									</div>
									<c:choose>
										<c:when test="${workbookScoreInfo.userAnswer[problemStatus.index] eq workbookTestInfo.problemDTOList.get(problemStatus.index).answer }">
											<div class="card card-body" style="background-color:#CCEECC;">
												<c:forEach var="choice" items="${problem.choice }" varStatus="choiceStatus">
													<c:choose>
														<c:when test="${choiceStatus.count eq workbookScoreInfo.userAnswer[problemStatus.index] }">
															<label><input type="radio" class="choiceAnswerInput mx-1" disabled="true" checked>${choiceStatus.count }. ${choice }</label>
														</c:when>
														<c:otherwise>
															<label><input type="radio" class="choiceAnswerInput mx-1" disabled="true">${choiceStatus.count }. ${choice }</label>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</div>
										</c:when>
										<c:otherwise>
											<div class="card card-body" style="background-color:#EECCCC;">
												<c:forEach var="choice" items="${problem.choice }" varStatus="choiceStatus">
													<c:choose>
														<c:when test="${choiceStatus.count eq workbookScoreInfo.userAnswer[problemStatus.index] }">
															<label><input type="radio" class="choiceAnswerInput mx-1" disabled="true" checked>${choiceStatus.count }. ${choice }</label>
														</c:when>
														<c:otherwise>
															<label><input type="radio" class="choiceAnswerInput mx-1" disabled="true">${choiceStatus.count }. ${choice }</label>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</div>
										</c:otherwise>
									</c:choose>
									<button type="button" class="btn btn-primary" data-bs-toggle="collapse" data-bs-target="#collapse${problemStatus.index }">정답 및 풀이 보기</button>
									<div class="collapse" id="collapse${problemStatus.index }">
										<div class="card card-body">
											정답 : ${problem.answer }<br>
											${problem.solution }
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise> <!--  주관식 -->
								<div class="my-3 p-3 d-flex flex-column" style="width:700px; background-color:#DDDDDD;">
									<div class="card card-body">
										<span class="fw-bold">${problemStatus.count }. ${problem.title }</span>
										<hr>
										${problem.content }
									</div>
									<c:choose>
										<c:when test="${workbookScoreInfo.userAnswer[problemStatus.index] eq workbookTestInfo.problemDTOList.get(problemStatus.index).answer }">
											<div class="card card-body" style="background-color:#CCEECC;">
												<input type="text" class="subjectiveAnswerInput form-control" value="${workbookScoreInfo.userAnswer[problemStatus.index] }" disabled="true">
											</div>
										</c:when>
										<c:otherwise>
											<div class="card card-body" style="background-color:#EECCCC;">
												<input type="text" class="subjectiveAnswerInput form-control" value="${workbookScoreInfo.userAnswer[problemStatus.index] }" disabled="true">
											</div>
										</c:otherwise>
									</c:choose>
									<button type="button" class="btn btn-primary" data-bs-toggle="collapse" data-bs-target="#collapse${problemStatus.index }">정답 및 풀이 보기</button>
									<div class="collapse" id="collapse${problemStatus.index }">
										<div class="card card-body">
											정답 : ${problem.answer }<br>
											${problem.solution }
										</div>
									</div>
								</div>							
							</c:otherwise>
						</c:choose>

					</c:forEach>
					<div class="d-flex justify-content-end align-items-start" style="width:700px;">
						<button onClick="location.href='/problem-bank/score-history-page';" type="button" class="btn btn-secondary">제출 이력</button>
						<button onClick="location.href='/problem-bank/workbook-test-page?workbookId=${workbookTestInfo.id}';" type="button" class="btn btn-dark mx-3">다시 풀기</button>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>