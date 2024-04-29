package com.kuwon.stugether.problemBank.workbook.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.problemBank.workbook.domain.Workbook;

@Mapper
public interface WorkbookRepository {
	public List<Workbook> selectWorkbookListByPage(@Param("userId") int userId
											, @Param("page") int page);
	
	public int selectProblemCountByWorkBookId(@Param("workbookId") int workbookId);

	
	public int insertWorkbook(@Param("workbook") Workbook workbook);
	
	
	public Workbook selectWorkbook(@Param("workbookId") int workbookId);
	
	public int selectWorkbookCountByUserId(@Param("userId") int userId);
	
	public int deleteWorkbook(@Param("workbookId") int workbookId);
	
	public List<Workbook> selectWorkbookByTitle(@Param("search") String search
											, @Param("page") Integer page);
	public List<Workbook> selectWorkbookByIdList(@Param("userIdList") List<Integer> userIdList
												, @Param("page") Integer page);
	
	public int selectWorkbookCountByTitle(@Param("search") String search);
	public int selectWorkbookCountByIdList(@Param("userIdList") List<Integer> userIdList);
}
