package com.kuwon.stugether.group.reply.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupReply {
	private int id;
	private int groupPostId;
	private int userId;
	private String Content;
	private Date createdAt;
	private Date updatedAt;
}
