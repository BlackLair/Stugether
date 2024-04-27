package com.kuwon.stugether.problemBank.workbook.dto;

import com.kuwon.stugether.problemBank.workbook.domain.Workbook;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkbookInfo {
	private int id;
	private int userId;
	private String userNickname;
	private String title;
	private int problemCount;
	private int myScore;
	private boolean liked;
	public WorkbookInfo(Workbook workbook, String userNickname, int problemCount, int myScore) {
		this.id = workbook.getId();
		this.userId = workbook.getUserId();
		this.userNickname = userNickname;
		this.title = workbook.getTitle();
		this.problemCount = problemCount;
		this.myScore = myScore;
	}
}
