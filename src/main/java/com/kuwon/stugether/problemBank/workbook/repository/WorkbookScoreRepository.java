package com.kuwon.stugether.problemBank.workbook.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kuwon.stugether.problemBank.workbook.domain.WorkbookScore;

@Mapper
public interface WorkbookScoreRepository {
	public Integer selectScore(@Param("workbookId") int workbookId
			, @Param("userId") int userId);
	public int insertScore(@Param("workbookScore") WorkbookScore workbookScore);
	
	public WorkbookScore selectScoreById(@Param("userId") int userId
										, @Param("scoreId") int scoreId);
	
	public List<WorkbookScore> selectScoreListByPage(@Param("userId") int userId
													, @Param("page") int page);
	public int deleteScoreByWorkbookId(@Param("workbookId") int workbookId);
	
	public int selectScoreCountByUserId(@Param("userId") int userId);
}
