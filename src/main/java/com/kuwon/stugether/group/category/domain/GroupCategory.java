package com.kuwon.stugether.group.category.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupCategory {
	private int id;
	private int groupId;
	private String name;
	private Date createdAt;
	private Date updatedAt;
}
