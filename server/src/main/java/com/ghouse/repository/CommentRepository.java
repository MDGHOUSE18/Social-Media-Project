package com.ghouse.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ghouse.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	

}
