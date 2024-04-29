package com.kuwon.stugether.problemBank.workbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import com.kuwon.stugether.problemBank.problem.domain.Problem;
import com.kuwon.stugether.problemBank.problem.dto.ProblemDTO;
import com.kuwon.stugether.problemBank.problem.repository.ProblemRepository;
import com.kuwon.stugether.problemBank.workbook.domain.Workbook;
import com.kuwon.stugether.problemBank.workbook.domain.WorkbookProblem;
import com.kuwon.stugether.problemBank.workbook.domain.WorkbookScore;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookInfo;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookInfoListDTO;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookScoreInfo;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookScoreListInfo;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookTestInfo;
import com.kuwon.stugether.problemBank.workbook.repository.WorkbookFavoriteRepository;
import com.kuwon.stugether.problemBank.workbook.repository.WorkbookProblemRepository;
import com.kuwon.stugether.problemBank.workbook.repository.WorkbookRepository;
import com.kuwon.stugether.problemBank.workbook.repository.WorkbookScoreRepository;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class WorkbookService {
	@Autowired
	ProblemRepository problemRepository;
	@Autowired
	WorkbookRepository workbookRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	WorkbookProblemRepository workbookProblemRepository;
	@Autowired
	WorkbookScoreRepository workbookScoreRepository;
	@Autowired
	WorkbookFavoriteRepository workbookFavoriteRepository;
	
	// 나의 문제집 목록 가져오기
	public List<WorkbookInfo> getMyWorkbookList(int userId, Integer page){
		List<Workbook> workbookList = workbookRepository.selectWorkbookListByPage(userId, (page - 1) * 10);
		List<WorkbookInfo> workbookInfoList = new ArrayList<>();
		User user = userRepository.selectById(userId); // 자신이 만든 문제집만 가져오므로 닉네임은 자신의 닉네임만 필요
		String userNickname = user.getNickname();
		for(Workbook workbook : workbookList) {
			int problemCount = workbookRepository.selectProblemCountByWorkBookId(workbook.getId());
			Integer score = workbookScoreRepository.selectScore(workbook.getId(), userId);
			if(score == null) // 문제집 푼 기록이 없는 경우
				score = 0;
			WorkbookInfo workbookInfo = new WorkbookInfo(workbook, userNickname, problemCount, score);
			workbookInfoList.add(workbookInfo);
		}
		return workbookInfoList;
	}
	// 문제집 검색
	public WorkbookInfoListDTO searchWorkbookList(int page, String type, String search, int userId){
		WorkbookInfoListDTO workbookInfoListDTO = new WorkbookInfoListDTO();
		List<WorkbookInfo> workbookInfoList = new ArrayList<>();
		if(type == null || search == null) {
			workbookInfoListDTO.setWorkbookInfoList(workbookInfoList);
			return workbookInfoListDTO;	
		}
		
		List<Workbook> workbookList = new ArrayList<>();
		if(type.equals("workbookId")) {  // 번호로 검색
			int workbookId = Integer.parseInt(search);
			Workbook workbook = workbookRepository.selectWorkbook(workbookId);
			if(workbook != null) {
				workbookList.add(workbook);
			}
		}else if(type.equals("title")){  // 이름으로 검색
			List<Workbook> workbookListSearched = workbookRepository.selectWorkbookByTitle(search, (page - 1) * 10);
			if(workbookListSearched != null)
			workbookList = workbookListSearched;
			workbookInfoListDTO.setTotalCount(workbookRepository.selectWorkbookCountByTitle(search));
		}else{// 닉네임으로 검색
			List<User> userList = userRepository.selectUserByNickname(search);
			List<Integer> userIdList = new ArrayList<>();
			for(User user : userList) {
				userIdList.add(user.getId());
			}
			if(userIdList.size() > 0)
				workbookList = workbookRepository.selectWorkbookByIdList(userIdList, (page - 1) * 10);
			workbookInfoListDTO.setTotalCount(workbookRepository.selectWorkbookCountByIdList(userIdList));
		}

		for(Workbook workbook : workbookList) {
			int creatorId = workbook.getUserId();
			User user = userRepository.selectById(creatorId);
			String creatorNickname = user.getNickname();
			int problemCount = workbookRepository.selectProblemCountByWorkBookId(workbook.getId());
			Integer score = workbookScoreRepository.selectScore(workbook.getId(), userId);
			if(score == null) // 문제집 푼 기록이 없는 경우
				score = 0;
			WorkbookInfo workbookInfo = new WorkbookInfo(workbook, creatorNickname, problemCount, score);
			if(workbookFavoriteRepository.selectWorkbookFavorite(userId, workbookInfo.getId()) > 0)
				workbookInfo.setLiked(true);
			workbookInfoList.add(workbookInfo);
		}
		workbookInfoListDTO.setWorkbookInfoList(workbookInfoList);
		return workbookInfoListDTO;
	}
	// 문제집 생성
	@Transactional
	public String addWorkbook(int userId, String title, Integer[] problemIdList, Workbook workbook) {
		if(problemIdList == null || problemIdList.length == 0) {
			return "empty";
		}
		workbook.setUserId(userId);
		workbook.setTitle(HtmlUtils.htmlEscape(title));
		if(workbookRepository.insertWorkbook(workbook) == 0) {
			return "failure";
		}
		int workbookId = workbook.getId();
		for(int problemId : problemIdList) {
			workbookProblemRepository.insertProblem(workbookId, problemId);
		}
		return "success";
	}
	// 문제집 삭제
	@Transactional
	public String removeWorkbook(int userId, int workbookId) {
		Workbook workbook = workbookRepository.selectWorkbook(workbookId);
		if(workbook.getUserId() != userId) {
			return "permission denied";
		}
		workbookProblemRepository.deleteAllProblemFromWorkbook(workbookId);
		workbookRepository.deleteWorkbook(workbookId);
		workbookScoreRepository.deleteScoreByWorkbookId(workbookId);
		workbookFavoriteRepository.deleteWorkbookFavoriteByWorkbookId(workbookId);
		return "success";
	}
	public int getWorkbookCountByUserId(int userId) {
		return workbookRepository.selectWorkbookCountByUserId(userId);
	}
	// 문제집 응시를 위한 문제 정보
	public WorkbookTestInfo getProblemListforTest(int workbookId){
		Workbook workbook = workbookRepository.selectWorkbook(workbookId);
		if(workbook == null) {
			return null;
		}
		List<WorkbookProblem> workbookProblemList = workbookProblemRepository.selectWorkbookProblemList(workbookId);
		List<ProblemDTO> problemDTOList = new ArrayList<>();
		for(WorkbookProblem workbookProblem : workbookProblemList) {
			int problemId = workbookProblem.getProblemId();
			Problem problem = problemRepository.selectProblemById(problemId);
			ProblemDTO problemDTO = new ProblemDTO();
			problemDTO.generateDTO(problem);
			problemDTO.removeAnswer();
			problemDTOList.add(problemDTO);
		}
		String userNickname = userRepository.selectById(workbook.getUserId()).getNickname();
		WorkbookTestInfo workbookTestInfo = new WorkbookTestInfo();
		workbookTestInfo.generate(workbook, problemDTOList, userNickname);
		return workbookTestInfo;
	}
	// 답안 제출
	public int submitAnswer(int userId, int workbookId, String[] answer) {
		int score = 0;
		List<WorkbookProblem> workbookProblemList = workbookProblemRepository.selectWorkbookProblemList(workbookId);
		for(int i = 0; i < answer.length; i++) {
			WorkbookProblem workbookProblem = workbookProblemList.get(i);
			int problemId = workbookProblem.getProblemId();
			Problem problem = problemRepository.selectProblemById(problemId);
			if(problem.getAnswer().equals(answer[i])) {
				score++;
			}
			
		}
		WorkbookScore workbookScore = new WorkbookScore();
		workbookScore.setUserId(userId);
		workbookScore.setWorkbookId(workbookId);
		workbookScore.setScore(score);
		String ans = "";
		for(String str : answer) {
			ans = ans + "#####" + HtmlUtils.htmlEscape(str);
		}
		ans = ans.replaceFirst("#####", "");
		workbookScore.setAnswer(ans);
		if(workbookScoreRepository.insertScore(workbookScore) == 1) {
			return workbookScore.getId();
		}
		return -1;
	}
	// 단일 채점 결과
	public WorkbookScoreInfo getResult(int userId, int scoreId){
		WorkbookScore workbookScore = workbookScoreRepository.selectScoreById(userId, scoreId);
		if(workbookScore == null)
			return null;
		int workbookId = workbookScore.getWorkbookId();
		Workbook workbook = workbookRepository.selectWorkbook(workbookId);
		if(workbook == null) {
			return null;
		}
		List<WorkbookProblem> workbookProblemList = workbookProblemRepository.selectWorkbookProblemList(workbookId);
		List<ProblemDTO> problemDTOList = new ArrayList<>();
		for(WorkbookProblem workbookProblem : workbookProblemList) {
			int problemId = workbookProblem.getProblemId();
			Problem problem = problemRepository.selectProblemById(problemId);
			ProblemDTO problemDTO = new ProblemDTO();
			problemDTO.generateDTO(problem);
			problemDTOList.add(problemDTO);
		}
		String userNickname = userRepository.selectById(workbook.getUserId()).getNickname();
		WorkbookTestInfo workbookTestInfo = new WorkbookTestInfo();
		workbookTestInfo.generate(workbook, problemDTOList, userNickname);
		
		WorkbookScoreInfo workbookScoreInfo = new WorkbookScoreInfo();
		workbookScoreInfo.setWorkbookTestInfo(workbookTestInfo);
		workbookScoreInfo.setUserAnswer(workbookScore.getAnswer().split("#####"));
		workbookScoreInfo.setScore(workbookScore.getScore());
		workbookScoreInfo.setCreatedAt(workbookScore.getCreatedAt());
		return workbookScoreInfo;
	}
	// 문제집 제출 이력 개수
	public int getScoreCount(int userId) {
		return workbookScoreRepository.selectScoreCountByUserId(userId);
	}
	// 문제집 제출 이력 리스트 페이지 단위로 가져옴
	public List<WorkbookScoreListInfo> getWorkbookScoreListByPage(int userId, int page){
		List<WorkbookScore> workbookScoreList = workbookScoreRepository.selectScoreListByPage(userId, (page - 1) * 10);
		List<WorkbookScoreListInfo> workbookScoreListInfoList = new ArrayList<>();
		for(WorkbookScore workbookScore : workbookScoreList) {
			WorkbookScoreListInfo workbookScoreListInfo = new WorkbookScoreListInfo();
			workbookScoreListInfo.setId(workbookScore.getId());
			int problemCount = workbookRepository.selectProblemCountByWorkBookId(workbookScore.getWorkbookId());
			workbookScoreListInfo.setProblemCount(problemCount);
			workbookScoreListInfo.setScore(workbookScore.getScore());
			Workbook workbook = workbookRepository.selectWorkbook(workbookScore.getWorkbookId());
			workbookScoreListInfo.setTitle(workbook.getTitle());
			workbookScoreListInfo.setCreatedAt(workbookScore.getCreatedAt());
			workbookScoreListInfoList.add(workbookScoreListInfo);
		}
		return workbookScoreListInfoList;
	}
	// 문제집 즐겨찾기 추가
	public String addWorkbookFavorite(int userId, int workbookId) {
		if(workbookFavoriteRepository.selectWorkbookFavorite(userId, workbookId) == 0) {
			if(workbookFavoriteRepository.insertWorkbookFavorite(userId, workbookId) == 1) {
				return "success";
			}
		}
		return "failure";
	}
	// 문제집 즐겨찾기 삭제
	public String removeWorkbookFavorite(int userId, int workbookId) {
		if(workbookFavoriteRepository.deleteWorkbookFavorite(userId, workbookId) == 1)
			return "success";
		return "failure";
	}
	// 즐겨찾기한 문제집 목록 가져오기
	public List<WorkbookInfo> getFavoriteWorkbookList(int userId, int page){
		List<Integer> workbookIdList = workbookFavoriteRepository.selectWorkbookFavoriteByUserId(userId, (page - 1) * 10);
		List<WorkbookInfo> workbookInfoList = new ArrayList<>();
		for(int workbookId : workbookIdList) {
			Workbook workbook = workbookRepository.selectWorkbook(workbookId);
					
			int creatorId = workbook.getUserId();
			User user = userRepository.selectById(creatorId);
			String creatorNickname = user.getNickname();
			int problemCount = workbookRepository.selectProblemCountByWorkBookId(workbook.getId());
			Integer score = workbookScoreRepository.selectScore(workbook.getId(), userId);
			if(score == null) // 문제집 푼 기록이 없는 경우
				score = 0;
			WorkbookInfo workbookInfo = new WorkbookInfo(workbook, creatorNickname, problemCount, score);
			workbookInfoList.add(workbookInfo);
		}
		return workbookInfoList;
	}
	public int getFavoriteWorkbookCount(int userId) {
		return workbookFavoriteRepository.selectWorkbookFavoriteCountByUserId(userId);
	}
}
