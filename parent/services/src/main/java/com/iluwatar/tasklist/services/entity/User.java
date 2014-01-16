package com.iluwatar.tasklist.services.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@Column(nullable=false)
	private String salt;
	
	@Column(nullable=false)
	private String name = "";
	
	@OneToMany(mappedBy="user", orphanRemoval=true)
	private Set<Tasklist> tasklists;
	
	public User() {
	}
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}
