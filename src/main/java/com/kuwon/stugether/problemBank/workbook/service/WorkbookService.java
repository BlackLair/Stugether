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
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookScoreInfo;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookTestInfo;
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
	
	// 나의 문제집 목록 가져오기
	public List<WorkbookInfo> getMyWorkbookList(int userId, Integer page){
		List<Workbook> workbookList = workbookRepository.selectWorkbookList(userId, (page - 1) * 10);
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
	
	public String removeWorkbook(int userId, int workbookId) {
		Workbook workbook = workbookRepository.selectWorkbook(workbookId);
		if(workbook.getUserId() != userId) {
			return "permission denied";
		}
		workbookProblemRepository.deleteProblemFromWorkbook(workbookId);
		workbookRepository.deleteWorkbook(workbookId);
		return "success";
	}
	
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
			ans = ans + "#####" + str;
		}
		ans = ans.replaceFirst("#####", "");
		workbookScore.setAnswer(ans);
		if(workbookScoreRepository.insertScore(workbookScore) == 1) {
			return workbookScore.getId();
		}
		return -1;
	}
	
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
	
}
