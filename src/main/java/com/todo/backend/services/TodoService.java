package com.todo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.todo.backend.entities.LoggedInResponse;
import com.todo.backend.entities.Todo;
import com.todo.backend.repo.TodoRepo;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepo repo;
	
	//private long n=0;
	public Todo createTodo(Todo todo) {
		repo.save(todo);
		return todo;
	}
	
	public List<Todo> getTodos(){
		return repo.findAll();
	}
	
	public Todo getTodo(long id) {
		return repo.findById(id).get();
	}
	
	public void deleteTodo(long id){
		repo.deleteById(id);
	}
	
	public Todo updateTodo(Todo todo,long id) {
		if(id==-1 || id == 0) {
			repo.save(todo);
			return todo;
		}
		Todo updatedTodo = getTodo(id);
		updatedTodo.setDescription(todo.getDescription());
		updatedTodo.setStatus(todo.getStatus());
		updatedTodo.setWorker(todo.getWorker());
		repo.save(updatedTodo);
		return updatedTodo;
		
	}
}
