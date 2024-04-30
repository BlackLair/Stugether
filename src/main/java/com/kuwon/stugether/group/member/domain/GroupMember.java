package com.kuwon.stugether.group.member.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupMember {
	private int id;
	private int userId;
	private int groupId;
	private Date createdAt;
}
