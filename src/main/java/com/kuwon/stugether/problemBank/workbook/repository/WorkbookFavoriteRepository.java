package com.kuwon.stugether.problemBank.workbook.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WorkbookFavoriteRepository {
	public int selectWorkbookFavorite(@Param("userId") int userId
									, @Param("workbookId") int workbookId);
	public int insertWorkbookFavorite(@Param("userId") int userId
									, @Param("workbookId") int workbookId);
	public int deleteWorkbookFavorite(@Param("userId") int userId
									, @Param("workbookId") int workbookId);
	public int deleteWorkbookFavoriteByWorkbookId(@Param("workbookId") int workbookId);
	
	public List<Integer> selectWorkbookFavoriteByUserId(@Param("userId") int userId
													, @Param("page") int page);
	public int selectWorkbookFavoriteCountByUserId(@Param("userId") int userId);
}
