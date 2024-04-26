package com.kuwon.stugether.problemBank.problem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import com.kuwon.stugether.common.FileManager;
import com.kuwon.stugether.problemBank.problem.domain.Problem;
import com.kuwon.stugether.problemBank.problem.dto.ProblemDTO;
import com.kuwon.stugether.problemBank.problem.dto.ProblemInfoDTO;
import com.kuwon.stugether.problemBank.problem.repository.ProblemRepository;
import com.kuwon.stugether.problemBank.workbook.repository.WorkbookProblemRepository;
import com.kuwon.stugether.problemBank.workbook.repository.WorkbookScoreRepository;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class ProblemService {
	@Autowired
	ProblemRepository problemRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	WorkbookProblemRepository workbookProblemRepository;
	@Autowired
	WorkbookScoreRepository workbookScoreRepository;
	
	// 문제 목록 불러오기
	public List<ProblemInfoDTO> getProblemListByPage(int userId, Integer page){
		if(page == null) page = 1;
		List<Problem> problemList = problemRepository.selectProblemListByPage(userId, (page - 1) * 10);
		List<ProblemInfoDTO> problemInfoDTOList = new ArrayList<>();
		for(Problem problem : problemList) {
			ProblemInfoDTO problemDTO = new ProblemInfoDTO();
			problemDTO.setId(problem.getId());
			problemDTO.setTitle(problem.getTitle());
			problemDTO.setUserId(problem.getUserId());
			User user = userRepository.selectById(userId);
			problemDTO.setUserNickname(user.getNickname());
			if(problem.getChoice() == null) {
				problemDTO.setType("주관식");
			}else {
				problemDTO.setType("객관식");
			}
			problemInfoDTOList.add(problemDTO);
		}
		return problemInfoDTOList;
	}
	
	// 문제 개수 가져오기
	public int getProblemCountByUserId(int userId) {
		return problemRepository.selectProblemCount(userId);
	}
	
	// 단일 문제 정보 가져오기
	public ProblemDTO getProblem(int problemId) {
		Problem problem = problemRepository.selectProblemById(problemId);
		if(problem == null)
			return null;
		ProblemDTO problemDTO = new ProblemDTO();
		problemDTO.generateDTO(problem);
		User user = userRepository.selectById(problem.getUserId());
		problemDTO.setUserNickname(user.getNickname());
		return problemDTO;
	}
	
	public ProblemDTO getProblem(int problemId, int userId) {
		Problem problem = problemRepository.selectProblemByIdAndUserId(problemId, userId);
		if(problem == null)
			return null;
		ProblemDTO problemDTO = new ProblemDTO();
		problemDTO.generateDTO(problem);
		User user = userRepository.selectById(problem.getUserId());
		problemDTO.setUserNickname(user.getNickname());
		return problemDTO;
	}
	
	// 신규 문제 생성
	public String addProblem(int userId
						, String title
						, String content
						, String answer
						, String[] choiceList
						, String solution
						, String editorToken
						, Problem problem) {
		
		// 글에 포함된 image 태그의 src 주소 변경
		String currentTime = System.currentTimeMillis() + "";
		content = content.replaceAll("/temp/" + userId + "_" + editorToken, "/problem/" + userId + "_" + currentTime);		
		// 글에 포함된 이미지 파일들을 임시 폴더에서 보관 폴더로 이동, 글 정보를 DB에 저장
		String filePath = FileManager.saveImage(userId, FileManager.TYPE_PROBLEM, currentTime, editorToken);
		
		problem.setUserId(userId);
		problem.setTitle(HtmlUtils.htmlEscape(title));
		problem.setImagePath(filePath);
		problem.setSolution(HtmlUtils.htmlEscape(solution));
		problem.setContent(content);
		problem.setAnswer(HtmlUtils.htmlEscape(answer));
		String choiceStr = null;
		if(choiceList != null) {
			choiceStr = "";
			for(String singleChoice : choiceList) {
				choiceStr = choiceStr + "#####";
				choiceStr = choiceStr + HtmlUtils.htmlEscape(singleChoice);
			}
			choiceStr = choiceStr.replaceFirst("#####", "");
		}
		problem.setChoice(choiceStr);
		
		if(problemRepository.insertProblem(problem) == 0) {
			return "failure";
		}
		return "success";
	}
	
	@Transactional
	public String removeProblem(int userId, int problemId) throws Exception {
		Problem problem = problemRepository.selectProblemById(problemId);
		if(problem == null) {
			return "not exist";
		}
		if(problem.getUserId() != userId) {
			return "permission denied";
		}
		
		String imagePath = problem.getImagePath();
		
		if(problemRepository.deleteProblem(problemId) == 1) {
			FileManager.deleteImage(imagePath);
			List<Integer> workbookIdList = workbookProblemRepository.selectWorkbookIdListByProblemId(problemId);
			for(int workbookId : workbookIdList) {
				workbookScoreRepository.deleteScoreByWorkbookId(workbookId);
			}
			workbookProblemRepository.deleteProblemFromAllWorkbook(problemId);
			return "success";
		}
		return "failure";
	}
}
