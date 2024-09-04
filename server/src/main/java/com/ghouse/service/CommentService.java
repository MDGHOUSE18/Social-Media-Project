package com.ghouse.service;

import com.ghouse.exceptions.CommentException;
import com.ghouse.model.Comment;



public interface CommentService {

	public Comment createComment(Comment comment, Integer postId, Integer userId) throws CommentException;

	public Comment findCommentById(Integer commentId) throws CommentException;
	
	public Comment likeComment(Integer commentId, Integer userId) throws CommentException;
	
	
}
