package com.kuwon.stugether.problemBank.workbook.dto;

import java.util.List;

import com.kuwon.stugether.problemBank.problem.dto.ProblemDTO;
import com.kuwon.stugether.problemBank.workbook.domain.Workbook;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkbookTestInfo {
	private int id;
	private int userId;
	private String userNickname;
	private String title;
	private int problemCount;
	private List<ProblemDTO> problemDTOList;
	
	public void generate(Workbook workbook, List<ProblemDTO> problemDTOList, String userNickname) {
		this.id = workbook.getId();
		this.userId = workbook.getUserId();
		this.userNickname = userNickname;
		this.title = workbook.getTitle();
		this.problemCount = problemDTOList.size();
		this.problemDTOList = problemDTOList;
	}
}
