package com.kuwon.stugether.problemBank.workbook.repository;

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
}
