package com.kuwon.stugether.problemBank.workbook.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkbookProblem {
	private int id;
	private int workbookId;
	private int problemId;
	private Date createdAt;
	
}
