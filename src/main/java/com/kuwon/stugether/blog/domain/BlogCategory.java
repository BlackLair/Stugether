package com.kuwon.stugether.blog.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogCategory {
	private int id;
	private int userId;
	private String name;
	private Date createdAt;
	private Date updatedAt;
}
