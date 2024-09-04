package com.ghouse.service;

import java.util.List;

import com.ghouse.exceptions.PostException;
import com.ghouse.model.Post;

public interface PostService {
    
    Post createPost(Post post, Integer userId) throws PostException;

    String deletePost(Integer postId, Integer userId) throws PostException;

    List<Post> findPostByUserId(Integer userId) throws PostException;

    Post findPostById(Integer postId) throws PostException;

    List<Post> findAllPost() throws PostException;

    Post savedPost(Integer postId, Integer userId) throws PostException;

    Post likePost(Integer postId, Integer userId) throws PostException;
    
    Post editPost(Integer postId,Post post) throws PostException;
}
