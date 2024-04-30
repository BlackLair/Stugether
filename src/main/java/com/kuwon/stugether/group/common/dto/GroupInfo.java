package com.kuwon.stugether.group.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupInfo implements Comparable<GroupInfo>{
	private int id;
	private int userId;
	private String userNickname;
	private String groupName;
	private String description;
	private int memberCount;
	
	@Override
	public int compareTo(GroupInfo g) {
		return memberCount - g.memberCount;
	}
}
