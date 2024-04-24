package com.kuwon.stugether.problemBank.workbook.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkbookScore {
	private int id;
	private int userId;
	private int workbookId;
	private int score;
	private String answer;
	private Date createdAt;
	private Date updatedAt;
}
