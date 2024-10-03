package com.blogrenukapp.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotNull
private String name;

@Column(nullable = false, unique = true)
private String email;

@NotNull
private String password;

@NotNull
private String about;

@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
private List<Post>posts=new ArrayList<>();
}
