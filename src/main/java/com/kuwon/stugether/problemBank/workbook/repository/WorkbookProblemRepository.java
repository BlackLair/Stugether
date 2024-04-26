package com.kuwon.stugether.problemBank.workbook.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.problemBank.workbook.domain.WorkbookProblem;

@Mapper
public interface WorkbookProblemRepository {
	public int insertProblem(@Param("workbookId") int workbookId
			, @Param("problemId") int problemId);
	public int deleteAllProblemFromWorkbook(@Param("workbookId") int workbookId);
	
	public List<WorkbookProblem> selectWorkbookProblemList(@Param("workbookId") int workbookId);
	
	public List<Integer> selectWorkbookIdListByProblemId(@Param("problemId") int problemId);
	
	public int deleteProblemFromAllWorkbook(@Param("problemId") int problemId);
}
