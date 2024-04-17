package com.kuwon.stugether.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.kuwon.stugether.blog.domain.BlogMemo;
import com.kuwon.stugether.blog.repository.BlogMemoRepository;

@Service
public class BlogMemoService {
	@Autowired
	BlogMemoRepository blogMemoRepository;
	
	public List<BlogMemo> getBlogMemoList(int userId){
		return blogMemoRepository.selectBlogMemoList(userId);
	}
	
	public boolean sortMemo(int userId, int[] memoIdList) {
		int index = 0;
		for(int memoId : memoIdList) {
			if(blogMemoRepository.updateBlogMemoOrder(userId, memoId, index++) == 0)
				return false;
		}
		return true;
	}
	
	public int addMemo(int userId, String content) {
		String htmlEscapeContent = HtmlUtils.htmlEscape(content);
		return  blogMemoRepository.insertBlogMemo(userId, htmlEscapeContent);
	}
	
	public int removeMemo(int memoId, int userId) {
		return blogMemoRepository.deleteBlogMemo(memoId, userId);
	}
}
