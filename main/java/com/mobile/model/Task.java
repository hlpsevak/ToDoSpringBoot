package com.mobile.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int taskId;
	
	@Column
	String taskName;
	String taskDesc;
	String startDate;
	String endDate;
	String status="pending";
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private User user;
	
	public Task() {
	}
	
	public Task(User user) {
		super();
		this.user = user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	

	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
