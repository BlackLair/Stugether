package com.kuwon.stugether.group.post.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupPostInfo {
	private int id;
	private int groupId;
	private int groupCategoryId;
	private int userId;
	private String userNickname;
	private String title;
	private Date createdAt;
}