package com.ghouse.service;

import java.util.List;

import com.ghouse.model.Post;

public interface PostService {
    
    Post createPost(Post post, Integer userId) throws Exception;

    String deletePost(Integer postId, Integer userId) throws Exception;

    List<Post> findPostByUserId(Integer userId) throws Exception;

    Post findPostById(Integer postId) throws Exception;

    List<Post> findAllPost() throws Exception;

    Post savedPost(Integer postId, Integer userId) throws Exception;

    Post likePost(Integer postId, Integer userId) throws Exception;
    
    Post editPost(Integer postId,Post post) throws Exception;
}
