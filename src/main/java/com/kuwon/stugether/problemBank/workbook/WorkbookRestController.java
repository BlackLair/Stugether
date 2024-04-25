package com.kuwon.stugether.problemBank.workbook;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.problemBank.problem.dto.ProblemDTO;
import com.kuwon.stugether.problemBank.problem.service.ProblemService;
import com.kuwon.stugether.problemBank.workbook.domain.Workbook;
import com.kuwon.stugether.problemBank.workbook.service.WorkbookService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/problem-bank")
@RestController
public class WorkbookRestController {
	@Autowired
	ProblemService problemService;
	@Autowired
	WorkbookService workbookService;
	
	@GetMapping("/problem-exist")
	public Map<String, String> problemExist(@RequestParam("problemId") int problemId
										, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		ProblemDTO problemDTO = problemService.getProblem(problemId, userId);
		
		Map<String, String> resultMap = new HashMap<>();
		if(problemDTO == null) {
			resultMap.put("result", "not exist");
		}else {
			resultMap.put("result", "success");
			resultMap.put("title", problemDTO.getTitle());
			resultMap.put("type", problemDTO.getType());
		}
		return resultMap;
	}
	
	@PostMapping("/create-workbook")
	public Map<String, Object> createWorkbook(@RequestParam("title") String title
											, @RequestParam(value="problemIdList[]", required=false) Integer[] problemIdList
											, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Workbook workbook = new Workbook();
		String result = workbookService.addWorkbook(userId, title, problemIdList, workbook);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		if(result.equals("success")) {
			resultMap.put("workbookId", workbook.getId());
		}
		
		return resultMap;
	}
	
	@DeleteMapping("/remove-workbook")
	public Map<String, String> removeWorkbook(@RequestParam("workbookId") int workbookId
											, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		String result = workbookService.removeWorkbook(userId, workbookId);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	@PostMapping("/submit-answer")
	public Map<String, Object> submitAnswer(@RequestParam("workbookId") int workbookId
											, @RequestParam(value="answer[]", required=false) String[] answer
											, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		int scoreId = workbookService.submitAnswer(userId, workbookId, answer);
		Map<String, Object> resultMap = new HashMap<>();
		if(scoreId == -1) {
			resultMap.put("result", "failure");
		}else {
			resultMap.put("result", "success");
			resultMap.put("scoreId", scoreId);
		}
		return resultMap;
	}
}
