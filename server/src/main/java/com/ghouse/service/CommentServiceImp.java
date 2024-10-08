package com.ghouse.service;

import java.time.LocalDateTime;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghouse.exceptions.CommentException;
import com.ghouse.model.Comment;
import com.ghouse.model.Post;
import com.ghouse.model.User;
import com.ghouse.repository.CommentRepository;
import com.ghouse.repository.PostRepository;

@Service
public class CommentServiceImp implements CommentService{
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public Comment createComment(Comment comment, 
			Integer postId, 
			Integer userId) throws CommentException {
		User user = userService.findUserById(userId);
		Post post = postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        
        post.getComments().add(savedComment);
        
        postRepository.save(post);
        
		return savedComment;
	}

	
	@Override
	public Comment findCommentById(Integer commentId) throws CommentException{
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if(opt.isEmpty()) {
			throw new CommentException("Comment Not Exists");
		}
		return opt.get();
	}
	
	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws CommentException {
		Comment comment = findCommentById(commentId);
		User user = userService.findUserById(userId);
		
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}else {
			comment.getLiked().remove(user);
		}
		return commentRepository.save(comment);
	}

	

}
