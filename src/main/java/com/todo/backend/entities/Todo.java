package com.todo.backend.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TODO")
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="WORKER")
	private String worker;
	
	@Column(name="STATUS")
	private String status;
	
	public Todo() {}
	
	
	
	public Todo(long id, String description, String worker, String status) {
		super();
		this.id = id;
		this.description = description;
		this.worker = worker;
		this.status = status;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", worker=" + worker + ", status=" + status + "]";
	}
	
	
	
}
