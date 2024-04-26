package com.kuwon.stugether.problemBank.problem;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuwon.stugether.problemBank.problem.domain.Problem;
import com.kuwon.stugether.problemBank.problem.service.ProblemService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/problem-bank")
@RestController
public class ProblemRestController {
	@Autowired
	ProblemService problemService;
	
	@PostMapping("/create-problem")
	public Map<String, Object> createProblem(@RequestParam("title") String title
											, @RequestParam("content") String content
											, @RequestParam("answer") String answer
											, @RequestParam(value="choice[]", required=false) String[] choiceList
											, @RequestParam("solution") String solution
											, @RequestParam("editorToken") String editorToken
											, HttpSession session){
		int userId = (int)session.getAttribute("userId");
		Problem problem = new Problem();
		String result = problemService.addProblem(userId, title, content, answer, choiceList, solution, editorToken, problem);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		if(result.equals("success")) {
			resultMap.put("problemId", problem.getId());
		}
		return resultMap;
	}
	
	@DeleteMapping("/remove-problem")
	public Map<String, String> removeProblem(@RequestParam("problemId") int problemId
											, HttpSession session) throws Exception{
		int userId = (int) session.getAttribute("userId");
		String result = problemService.removeProblem(userId, problemId);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
}
