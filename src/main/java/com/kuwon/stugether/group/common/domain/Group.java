package com.kuwon.stugether.group.common.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Group {
	private int id;
	private int userId;
	private String groupName;
	private String description;
	private Date createdAt;
	private Date updatedAt;
}
