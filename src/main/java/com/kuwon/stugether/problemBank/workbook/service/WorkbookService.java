package com.kuwon.stugether.problemBank.workbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import com.kuwon.stugether.problemBank.problem.repository.ProblemRepository;
import com.kuwon.stugether.problemBank.workbook.domain.Workbook;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookInfo;
import com.kuwon.stugether.problemBank.workbook.repository.WorkbookRepository;
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
	// 나의 문제집 목록 가져오기
	public List<WorkbookInfo> getMyWorkbookList(int userId, Integer page){
		List<Workbook> workbookList = workbookRepository.selectWorkbookList(userId, (page - 1) * 10);
		List<WorkbookInfo> workbookInfoList = new ArrayList<>();
		User user = userRepository.selectById(userId); // 자신이 만든 문제집만 가져오므로 닉네임은 자신의 닉네임만 필요
		String userNickname = user.getNickname();
		for(Workbook workbook : workbookList) {
			int problemCount = workbookRepository.selectProblemCountByWorkBookId(workbook.getId());
			Integer score = workbookRepository.selectScore(workbook.getId(), userId);
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
			workbookRepository.insertProblem(workbookId, problemId);
		}
		return "success";
	}
	
	public String removeWorkbook(int userId, int workbookId) {
		Workbook workbook = workbookRepository.selectWorkbook(workbookId);
		if(workbook.getUserId() != userId) {
			return "permission denied";
		}
		workbookRepository.deleteProblemFromWorkbook(workbookId);
		workbookRepository.deleteWorkbook(workbookId);
		return "success";
	}
}
