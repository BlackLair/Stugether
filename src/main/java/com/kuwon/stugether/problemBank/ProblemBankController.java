package com.kuwon.stugether.problemBank;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/problem-bank")
@Controller
public class ProblemBankController {
	@GetMapping("/my-problem-page")
	public String myProblemView(@RequestParam(value="page", required=false) Integer page
							, HttpSession session, Model model) {
		if(page == null) page = 1;
		int userId = (int) session.getAttribute("userId");
		
		return "problemBank/my-problem-page";
	}
	
	@GetMapping("/problem-detail-page")
	public String problemDetailView(@RequestParam("problemId") int problemId
								, HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		
		return "problemBank/problem-detail-page";
	}
}
