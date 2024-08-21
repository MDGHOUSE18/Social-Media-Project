package com.ghouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ghouse.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	
	@Query("select P from Post P where P.id=:userId")
	List<Post> findPostByUserId(Integer userId);
	
}
