package com.kuwon.stugether.problemBank.workbook.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkbookScoreInfo {
	private WorkbookTestInfo workbookTestInfo;
	private String[] userAnswer;
	private int score;
	private Date createdAt;
}
