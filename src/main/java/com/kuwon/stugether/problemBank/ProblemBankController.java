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

import jakarta.servlet.http.HttpSession;

@RequestMapping("/problem-bank")
@Controller
public class ProblemBankController {
	@Autowired
	ProblemService problemService;
	
	@GetMapping("/my-problem-page")
	public String myProblemView(@RequestParam(value="page", required=false) Integer page
							, HttpSession session, Model model) {
		if(page == null) page = 1;
		int userId = (int) session.getAttribute("userId");
		List<ProblemInfoDTO> problemInfoDTOList = problemService.getProblemList(userId, page);
		int totalCount = problemService.getProblemCountByUserId(userId);
		model.addAttribute("problemInfoDTOList", problemInfoDTOList);
		model.addAttribute("pageCount", (totalCount / 10) + 1);
		return "problemBank/my-problem-page";
	}
	
	@GetMapping("/problem-detail-page")
	public String problemDetailView(@RequestParam("problemId") int problemId
								, @RequestParam(value="page", required=false) Integer page
								, HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		ProblemDTO problemDTO = problemService.getProblem(problemId);
		model.addAttribute("problemDTO", problemDTO);
		return "problemBank/problem-detail-page";
	}
	
	@GetMapping("/create-problem-page")
	public String createProblemView() {
		return "problemBank/create-problem-page";
	}
	
	@GetMapping("/my-workbook-page")
	public String myWorkbookView(@RequestParam(value="page", required=false) Integer page
								, HttpSession session, Model model) {
		if(page == null) page = 1;
		
		return "problemBank/my-workbook-page";
	}
	
	@GetMapping("/create-workbook-page")
	public String createWorkbookView() {
		return "problemBank/create-workbook-page";
	}
}
