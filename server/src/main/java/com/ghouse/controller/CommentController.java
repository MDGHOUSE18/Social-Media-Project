package com.ghouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghouse.model.Comment;
import com.ghouse.model.User;
import com.ghouse.service.CommentService;
import com.ghouse.service.UserService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/{postId}")
	public ResponseEntity<Comment> createComment(@RequestHeader("Authorization") String token,
	            @RequestBody Comment comment,
	            @PathVariable Integer postId) throws Exception {
	User user = userService.getUserFromToken(token);
	Comment createdComment = commentService.createComment(comment, postId, user.getId());
	return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
	}
	
	@PutMapping("/like/{commentId}")
    public ResponseEntity<Comment> likeComment(@RequestHeader("Authorization") String token,
                                               @PathVariable Integer commentId) throws Exception {
        User user = userService.getUserFromToken(token);
        Comment likedComment = commentService.likeComment(commentId, user.getId());
        return ResponseEntity.ok(likedComment);
    }
	
}
