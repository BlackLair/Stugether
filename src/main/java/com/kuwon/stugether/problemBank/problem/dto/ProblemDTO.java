package com.kuwon.stugether.problemBank.problem.dto;

import java.util.Date;

import com.kuwon.stugether.problemBank.problem.domain.Problem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemDTO {
	private int id;
	private int userId;
	private String userNickname;
	private String title;
	private String type;
	private String content;
	private String imagePath;
	private String answer;
	private String[] choice;
	private String solution;
	private Date createdAt;
	private Date updatedAt;
	
	public void setData(Problem problem) {
		id = problem.getId();
		userId = problem.getUserId();
		title = problem.getTitle();
		content = problem.getContent();
		imagePath = problem.getImagePath();
		answer = problem.getAnswer();
		if(problem.getIsMultipleChoice() == 1) {
			choice = problem.getChoice().split("[#]{5}");
			type = "객관식";
		}else {
			type = "주관식";
		}
		solution = problem.getSolution();
		createdAt = problem.getCreatedAt();
		updatedAt = problem.getUpdatedAt();
	}
}
