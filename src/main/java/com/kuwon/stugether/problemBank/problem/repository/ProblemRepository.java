package com.kuwon.stugether.problemBank.problem.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.problemBank.problem.domain.Problem;

@Mapper
public interface ProblemRepository {
	public List<Problem> selectProblemListByPage(@Param("userId") int userId
										, @Param("page") int page);
	public int selectProblemCount(@Param("userId") int userId);
	public Problem selectProblemById(@Param("problemId") int problemId);
	public Problem selectProblemByIdAndUserId(@Param("problemId") int problemId
											, @Param("userId") int userId);
	
	public int insertProblem(Problem problem);
	
	public int deleteProblem(@Param("problemId") int problemId);
	public List<Problem> selectProblemByUserId(@Param("userId") int userId);
}
