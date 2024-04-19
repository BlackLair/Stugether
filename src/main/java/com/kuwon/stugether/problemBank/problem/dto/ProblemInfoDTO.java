package com.kuwon.stugether.problemBank.problem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemInfoDTO {
	private int id;
	private int userId;
	private String userNickname;
	private String type;
	private String title;
}
