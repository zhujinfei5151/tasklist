package com.iluwatar.tasklist.services.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Task
 *
 */
@Entity
@Table(name="task", schema="tasklist")
public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	private String description;
	
	private boolean done;
	
	private Date donedate;
	
	private int ordernum;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Tasklist tasklist;
	
	public Task() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getDonedate() {
		return donedate;
	}

	public void setDonedate(Date donedate) {
		this.donedate = donedate;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public Tasklist getTasklist() {
		return tasklist;
	}

	public void setTasklist(Tasklist tasklist) {
		this.tasklist = tasklist;
	}
	
}
