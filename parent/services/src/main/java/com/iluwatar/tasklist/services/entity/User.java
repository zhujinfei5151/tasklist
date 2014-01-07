package com.iluwatar.tasklist.services.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="user", schema="tasklist")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique=true, nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String passwordHash;
	
	@OneToMany(mappedBy="user", orphanRemoval=true)
	private Set<Tasklist> tasklists;
	
	public User() {
	}

//	public User(User another) {
//		this.id = another.id;
//		this.username = another.username;
//		this.passwordHash = another.passwordHash;
//		this.tasklists = new HashSet<>();
//		this.tasklists.addAll(another.getTasklists());
//	}
	
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
   
	public Set<Tasklist> getTasklists() {
		return tasklists;
	}

	public void setTasklists(Set<Tasklist> tasklists) {
		this.tasklists = tasklists;
	}
	
}
