package com.example.demo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	UserDao userDAO;
	
	//to save an employee into database
	@PostMapping("/save")
	public Users createUser(@Valid @RequestBody Users user) {
		return userDAO.save(user);
	}
	
	//to get all users
	@GetMapping("/get")
	public List<Users> getAllUsers(){
		return userDAO.findall();
	}
	
	//get user by id
	@GetMapping("/get/{id}")
	public ResponseEntity<Users> getUserbyid(@PathVariable(value="id") Long userid){
		Users user = userDAO.finduser(userid);
		
		if(user==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}
	
	//update user by user id
	@PutMapping("/put/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable(value="id") Long id, @Valid @RequestBody Users user){
		
		Users user1 = userDAO.finduser(id);
		if(user1==null) {
			return ResponseEntity.notFound().build();
		}
		
		user1.setName(user.getName());
		user1.setId(user.getId());
		
		Users updateUser = userDAO.save(user1);
		return ResponseEntity.ok().body(updateUser);
	}
	
	//delete a user
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Users> deleteUser(@PathVariable(value="id") Long userid){
		Users user2 = userDAO.finduser(userid);
		if(user2==null) {
			return ResponseEntity.notFound().build();
		}
		
		userDAO.delete(user2);
		return ResponseEntity.ok().build();
	}	
}
