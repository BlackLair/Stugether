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

	
	public int insertWorkbook(@Param("workbook") Workbook workbook);
	
	
	public Workbook selectWorkbook(@Param("workbookId") int workbookId);
	
	public int deleteWorkbook(@Param("workbookId") int workbookId);
}
