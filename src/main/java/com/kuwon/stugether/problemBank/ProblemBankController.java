package com.kuwon.stugether.problemBank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuwon.stugether.problemBank.problem.dto.ProblemDTO;
import com.kuwon.stugether.problemBank.problem.dto.ProblemInfoDTO;
import com.kuwon.stugether.problemBank.problem.service.ProblemService;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookInfo;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookInfoListDTO;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookScoreInfo;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookScoreListInfo;
import com.kuwon.stugether.problemBank.workbook.dto.WorkbookTestInfo;
import com.kuwon.stugether.problemBank.workbook.service.WorkbookService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/problem-bank")
@Controller
public class ProblemBankController {
	@Autowired
	ProblemService problemService;
	@Autowired
	WorkbookService workbookService;
	// 나의 문제 목록 페이지
	@GetMapping("/my-problem-page")
	public String myProblemView(@RequestParam(value="page", defaultValue="1") Integer page
							, HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		List<ProblemInfoDTO> problemInfoDTOList = problemService.getProblemListByPage(userId, page);
		int totalCount = problemService.getProblemCountByUserId(userId);
		model.addAttribute("problemInfoDTOList", problemInfoDTOList);
		model.addAttribute("pageCount", (Math.max(0, totalCount - 1) / 10) + 1);
		return "problemBank/my-problem-page";
	}
	// 문제 상세보기 페이지
	@GetMapping("/problem-detail-page")
	public String problemDetailView(@RequestParam("problemId") int problemId
								, HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		ProblemDTO problemDTO = problemService.getProblem(problemId);
		model.addAttribute("problemDTO", problemDTO);
		return "problemBank/problem-detail-page";
	}
	// 문제 생성 페이지
	@GetMapping("/create-problem-page")
	public String createProblemView() {
		return "problemBank/create-problem-page";
	}
	// 나의 문제집 목록 페이지
	@GetMapping("/my-workbook-page")
	public String myWorkbookView(@RequestParam(value="page", defaultValue="1") Integer page
								, HttpSession session, Model model) {
		int userId = (int)session.getAttribute("userId");
		List<WorkbookInfo> workbookInfoList = workbookService.getMyWorkbookList(userId, page);
		int workbookCount = workbookService.getWorkbookCountByUserId(userId);
		model.addAttribute("pageCount", (Math.max(0, workbookCount - 1) / 10) + 1);
		model.addAttribute("workbookInfoList", workbookInfoList);
		return "problemBank/my-workbook-page";
	}
	// 문제집 생성 페이지
	@GetMapping("/create-workbook-page")
	public String createWorkbookView() {
		return "problemBank/create-workbook-page";
	}
	// 문제집 테스트 응시 페이지
	@GetMapping("/workbook-test-page")
	public String workbookTestView(@RequestParam("workbookId") int workbookId
								, Model model) {
		WorkbookTestInfo workbookTestInfo = workbookService.getProblemListforTest(workbookId);
		if(workbookTestInfo == null) {
			return "problemBank/workbook-not-exist-page";
		}
		model.addAttribute("workbookTestInfo", workbookTestInfo);
		return "problemBank/workbook-test-page";
	}
	// 문제집 채점 결과 페이지
	@GetMapping("/workbook-result-page")
	public String workbookResultView(@RequestParam("scoreId") int scoreId
									, HttpSession session, Model model) throws Exception {
		int userId = (int) session.getAttribute("userId");
		WorkbookScoreInfo workbookScoreInfo = workbookService.getResult(userId, scoreId);
		if(workbookScoreInfo == null) {
			return "problemBank/workbook-not-exist-page";
		}
		model.addAttribute(workbookScoreInfo);
		return "problemBank/workbook-result-page";
	}
	// 문제집 채점 결과 목록 페이지
	@GetMapping("/score-history-page")
	public String scoreHistoryView(@RequestParam(value="page", defaultValue="1") Integer page
								, HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		List<WorkbookScoreListInfo> workbookScoreList = workbookService.getWorkbookScoreListByPage(userId, page);
		int scoreCount = workbookService.getScoreCount(userId);
		model.addAttribute("workbookScoreList", workbookScoreList);
		model.addAttribute("pageCount", (Math.max(0, scoreCount - 1) / 10) + 1);
		return "problemBank/score-history-page";
	}
	// 문제집 검색 페이지
	@GetMapping("/search-workbook-page")
	public String searchWorkbookView(@RequestParam(value="page", defaultValue="1") Integer page
									, @RequestParam(value="type", required=false) String type
									, @RequestParam(value="search", required=false) String search
									, HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		WorkbookInfoListDTO workbookInfoListDTO = workbookService.searchWorkbookList(page, type, search, userId);
		model.addAttribute("workbookInfoList", workbookInfoListDTO.getWorkbookInfoList());
		int totalCount = workbookInfoListDTO.getTotalCount();
		model.addAttribute("pageCount", (Math.max(0, totalCount - 1) / 10) + 1);
		model.addAttribute("type", type);
		model.addAttribute("search", search);
		return "problemBank/search-workbook-page";
	}
	// 즐겨찾기한 문제집 목록 페이지
	@GetMapping("/favorite-workbook-page")
	public String favoriteWorkbookView(@RequestParam(value="page", defaultValue="1") Integer page
									, HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		List<WorkbookInfo> workbookInfoList = workbookService.getFavoriteWorkbookList(userId, page);
		int totalCount = workbookService.getFavoriteWorkbookCount(userId);
		model.addAttribute("pageCount", (Math.max(0,  totalCount - 1) / 10) + 1);
		model.addAttribute("workbookInfoList", workbookInfoList);
		return "problemBank/favorite-workbook-page";
	}
}
