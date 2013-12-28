package com.iluwatar.tasklist.services.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tasklist", schema="tasklist")
public class Tasklist implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@ManyToOne
	@JoinColumn(nullable=false)
	private User user;

	@OneToMany(mappedBy="tasklist", orphanRemoval=true)
	private Set<Task> tasks;
	
	public int getId() {
		return id;
	}
	
	public void setId(int value) {
		this.id = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
