package com.kuwon.stugether.problemBank.workbook.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Workbook {
	private int id;
	private int userId;
	private String title;
	private Date createdAt;
	private Date updatedAt;
}
