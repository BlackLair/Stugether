package com.kuwon.stugether.problemBank.workbook.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkbookScoreListInfo {
	private int id;
	private String title;
	private int score;
	private int problemCount;
	private Date createdAt;
}
