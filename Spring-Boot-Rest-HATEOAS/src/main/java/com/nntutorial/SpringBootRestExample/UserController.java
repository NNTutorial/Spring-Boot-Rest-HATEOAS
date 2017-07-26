package com.nntutorial.SpringBootRestExample;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	public UserResourceAssembler userResourceAssembler;
	
	@Autowired
	public Database database;
	
	@RequestMapping(value="/getallusers",method=RequestMethod.GET)
	public List<User> getAllUsers(){
		return database.getAllUsersList();
	}
	
	@RequestMapping(value="/getusers/{companyname}",method=RequestMethod.GET)
	public List<User> getUsersByCompanyName(@PathVariable String companyname) throws InvalidDataException {
		return database.getAllUsersByCompanyName(companyname);
	}
	
	@ExceptionHandler(InvalidDataException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String showException(Exception e) {
		return e.getMessage();
	}
	
	@RequestMapping(value="/getuserbyID/{userid}",method=RequestMethod.GET)
	public Resource<User> getUserByID(@PathVariable Integer userid) throws InvalidDataException {
		User user=database.getUserByID(userid);
		return this.userResourceAssembler.toResource(user);
	}

}
