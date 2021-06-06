package com.mobile.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobile.model.Task;
import com.mobile.model.User;
import com.mobile.repository.TaskRepository;

@Controller
@RequestMapping("/tasks")
@CrossOrigin(value="http://localhost:4200")
public class TaskController {
	
	@Autowired
	TaskRepository repo;
	
	@GetMapping("/")
	public ResponseEntity<List<Task>> getTasks(){
		return new ResponseEntity<List<Task>>(repo.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Void> storeTask(@RequestBody Task task){
		repo.save(task);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Void> updateTask(@RequestBody Task task){
		System.out.println("Update task called -------*****************************");
		repo.save(task);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{taskid}")
	public ResponseEntity<Void> deleteTask(@PathVariable("taskid") int taskid){
		repo.deleteById(taskid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
