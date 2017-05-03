package com.niit.collaboration.rest.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;



@RestController
public class UserRestService {
	
	
	@Autowired
	private User user;
	
	@Autowired
	private UserDAO userDAO;
	
	
	private Logger log = LoggerFactory.getLogger(UserRestService.class);

	@GetMapping("/hello/")
	public String sayHello()
	{
		return "  Hello from User rest service Modifed message";
	}
	
	@GetMapping("/users")
	public ResponseEntity< List<User>> getAllUser()
	{
		List<User> userList = userDAO.list();
		
		return   new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	
	//http://localhost:8080/CollaborationResetService/user/niit
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserByID(@PathVariable("id") String id)
	{
		log.debug("**************Starting of the method getUserByID");
		log.info("***************Trying to get userdetails of the id " + id);
		user = userDAO.get(id);
		
		if(user==null)
		{
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist with the id :" + id);
		}
		else
		{
			user.setErrorCode("200");
			user.setErrorMessage("success");
		}
		
		log.info("**************** Name of teh user is " + user.getName());
		log.debug("**************Ending of the method getUserByID");
	  return	new ResponseEntity<User>(user , HttpStatus.OK);
	}
	
	
	
	
	
	
}
