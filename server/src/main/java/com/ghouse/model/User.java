package com.ghouse.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private String gender;
	
	private List<Integer> followers = new ArrayList<>();
	
	private List<Integer> following=new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Post> savedPost=new ArrayList<>();
	
	
	
	
}
