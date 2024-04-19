package com.kuwon.stugether.problemBank.problem.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Problem {
	private int id;
	private int userId;
	private String title;
	private String content;
	private String imagePath;
	private int isMultipleChoice;
	private String answer;
	private String choice;
	private String solution;
	private Date createdAt;
	private Date updatedAt;
}
