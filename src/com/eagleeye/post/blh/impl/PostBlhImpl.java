package com.eagleeye.post.blh.impl;

import java.util.List;

import com.eagleeye.post.blh.IPostBlh;
import com.eagleeye.post.dao.impl.PostDAO;

public class PostBlhImpl implements IPostBlh {

	PostDAO postDao;

	@Override
	public List getTopNumPosts() {
		// TODO Auto-generated method stub
		return postDao.getTopNumPosts();
	}

	public PostDAO getPostDao() {
		return postDao;
	}

	public void setPostDao(PostDAO postDao) {
		this.postDao = postDao;
	}

}
