package com.kuwon.stugether.problemBank.workbook.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.problemBank.workbook.domain.Workbook;

@Mapper
public interface WorkbookRepository {
	public List<Workbook> selectWorkbookList(@Param("userId") int userId
											, @Param("page") int page);
	
	public int selectProblemCountByWorkBookId(@Param("workbookId") int workbookId);
	public Integer selectScore(@Param("workbookId") int workbookId
						, @Param("userId") int userId);
	
	public int insertWorkbook(@Param("workbook") Workbook workbook);
	public int insertProblem(@Param("workbookId") int workbookId
							, @Param("problemId") int problemId);
	
	public Workbook selectWorkbook(@Param("workbookId") int workbookId);
	public int deleteProblemFromWorkbook(@Param("workbookId") int workbookId);
	public int deleteWorkbook(@Param("workbookId") int workbookId);
}
