package com.ghouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ghouse.model.Post;
import com.ghouse.response.ApiResponse;
import com.ghouse.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping("/posts/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception{
		Post createPost = postService.createPost(post, userId);
		return new ResponseEntity<Post>(createPost, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception{

		String message= postService.deletePost(postId, userId);
		ApiResponse response=new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost() throws Exception{
		List<Post> post=postService.findAllPost();
		return new ResponseEntity<List<Post>>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception{
		Post posts=postService.findPostById(postId);
		return new ResponseEntity<Post>(posts,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Integer userId) throws Exception{
		List<Post> posts=postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/posts/{postId}/user/{userId}")
	public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		Post posts=postService.savedPost(postId, userId);
		return new ResponseEntity<Post>(posts,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/posts/like/{postId}/user/{userId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception{
		Post posts=postService.likePost(postId, userId);
		return new ResponseEntity<Post>(posts,HttpStatus.ACCEPTED);
	}
	
}
