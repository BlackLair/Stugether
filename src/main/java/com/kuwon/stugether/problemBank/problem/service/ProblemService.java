package com.kuwon.stugether.problemBank.problem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuwon.stugether.problemBank.problem.domain.Problem;
import com.kuwon.stugether.problemBank.problem.dto.ProblemDTO;
import com.kuwon.stugether.problemBank.problem.dto.ProblemInfoDTO;
import com.kuwon.stugether.problemBank.problem.repository.ProblemRepository;
import com.kuwon.stugether.user.domain.User;
import com.kuwon.stugether.user.repository.UserRepository;

@Service
public class ProblemService {
	@Autowired
	ProblemRepository problemRepository;
	@Autowired
	UserRepository userRepository;
	
	public List<ProblemInfoDTO> getProblemList(int userId, Integer page){
		if(page == null) page = 1;
		List<Problem> problemList = problemRepository.selectProblemList(userId, (page - 1) * 10);
		List<ProblemInfoDTO> problemInfoDTOList = new ArrayList<>();
		for(Problem problem : problemList) {
			ProblemInfoDTO problemDTO = new ProblemInfoDTO();
			problemDTO.setId(problem.getId());
			problemDTO.setTitle(problem.getTitle());
			problemDTO.setUserId(problem.getUserId());
			User user = userRepository.selectById(userId);
			problemDTO.setUserNickname(user.getNickname());
			problemInfoDTOList.add(problemDTO);
		}
		return problemInfoDTOList;
	}
	
	public int getProblemCountByUserId(int userId) {
		return problemRepository.selectProblemCount(userId);
	}
	
	public ProblemDTO getProblem(int problemId) {
		Problem problem = problemRepository.selectProblemById(problemId);
		ProblemDTO problemDTO = new ProblemDTO();
		problemDTO.generateDTO(problem);
		User user = userRepository.selectById(problem.getUserId());
		problemDTO.setUserNickname(user.getNickname());
		return problemDTO;
		
	}
}
