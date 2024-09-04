package com.ghouse.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghouse.exceptions.PostException;
import com.ghouse.model.Post;
import com.ghouse.model.User;
import com.ghouse.repository.PostRepository;
import com.ghouse.repository.UserRepository;

@Service
public class PostServiceImp implements PostService{

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Post createPost(Post post, Integer userId) throws PostException {
		
		User user=userService.findUserById(userId);
		
		Post newPost=new Post();
		
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setUser(user);
		
		return postRepository.save(newPost);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws PostException {
		Post post=findPostById(postId);
		User user= userService.findUserById(userId);
		if(post.getUser().getId()!=user.getId()) {
			throw new PostException("You can't delete another user post");
		}
		return "Post deleted sucessfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws PostException {
		
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws PostException {
		Optional<Post> post=postRepository.findById(postId);
		if(post.isEmpty()) {
			throw new PostException("Post is not fount with this id "+postId);
		}
		return post.get();
	}

	@Override
	public List<Post> findAllPost() throws PostException {
		
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws PostException {
		Post post=findPostById(postId);
		User user= userService.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws PostException {
		Post post=findPostById(postId);
		User user= userService.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}else {
			post.getLiked().add(user);
		}
		
		return postRepository.save(post);
	}

	@Override
	public Post editPost(Integer postId,Post post) throws PostException {
//		Post originalPost=findPostById(postId);
//		originalPost.setCaption(post.getCaption());
//		originalPost.set
		return null;
	}
	
	
	
}
