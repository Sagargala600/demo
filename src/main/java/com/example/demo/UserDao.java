package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
	
	@Autowired
	UserRepository userrepository;
	
	//To save a user
	public Users save(Users user) {
		return userrepository.save(user);
	}
	
	//search all users
	public List<Users> findall(){
		return userrepository.findAll();
	}	

	//get a user by id 
	public Users finduser(Long userid) {
		return userrepository.getOne(userid);
	}
	
	//delete a user
	public void delete(Users user) {
		userrepository.delete(user);
	}
}
