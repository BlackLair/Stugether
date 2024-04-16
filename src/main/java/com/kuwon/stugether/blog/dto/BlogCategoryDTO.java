package com.kuwon.stugether.blog.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogCategoryDTO {
	private List<BlogCategoryDetail> blogCategoryDetailList;
	private int allPostCount;
	private int currentPages;
	private Integer currentCategoryId;
}
