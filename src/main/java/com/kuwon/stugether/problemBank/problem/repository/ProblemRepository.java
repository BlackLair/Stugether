package com.kuwon.stugether.problemBank.problem.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.problemBank.problem.domain.Problem;

@Mapper
public interface ProblemRepository {
	public List<Problem> selectProblemList(@Param("userId") int userId
										, @Param("page") int page);
	public int selectProblemCount(@Param("userId") int userId);
	public Problem selectProblemById(@Param("problemId") int problemId);
}
