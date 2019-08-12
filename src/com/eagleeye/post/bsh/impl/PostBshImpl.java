package com.eagleeye.post.bsh.impl;

import java.util.List;

import com.eagleeye.post.blh.IPostBlh;
import com.eagleeye.post.bsh.IPostBsh;

public class PostBshImpl implements IPostBsh {
	IPostBlh postBlh;

	@Override
	public List getTopNumPosts() {
		// TODO Auto-generated method stub
		return postBlh.getTopNumPosts();
	}

	public IPostBlh getPostBlh() {
		return postBlh;
	}

	public void setPostBlh(IPostBlh postBlh) {
		this.postBlh = postBlh;
	}
}
