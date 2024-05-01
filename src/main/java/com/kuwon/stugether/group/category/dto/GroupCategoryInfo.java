package com.kuwon.stugether.group.category.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupCategoryInfo {
	private int id;
	private int groupId;
	private String name;
	private Date createdAt;
	private Date updatedAt;
	private int postCount;
}
