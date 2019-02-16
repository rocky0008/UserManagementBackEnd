package com.bridgeit.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.usermanagement.dto.UserDto;
import com.bridgeit.usermanagement.model.Response;
import com.bridgeit.usermanagement.model.User;
import com.bridgeit.usermanagement.service.IUserService;

@RestController
@CrossOrigin(origins = {})
public class UserController {

	Response response;
	@Autowired
	UserDto userDto;
	@Autowired
	IUserService userService;

	@RequestMapping("/")
	public String welcome() {
		System.out.println(userService);
		return "Welcome";
	}

//	@RequestMapping(value="/login" ,method=RequestMethod.POST)
	@PostMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody UserDto userInfo)

	{
		
		System.out.println(userInfo);
		response = new Response();
		if (userInfo.getEmail().equals(userDto.getEmail()) && userInfo.getPassword().equals(userDto.getPassword())) {
			response.setStatus("done");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

		else {
			response.setStatus("not valid");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}

	}

	
	@PostMapping("/user")
	public ResponseEntity<Response> addUser(@RequestBody User user)
	{
		userService.createUser(user);
		response=new Response();
		response.setStatus("done");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}	
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> userList=userService.getAllUser();
		response = new Response();
		response.setStatus("done");
		System.out.println("hihihi");
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<Response> updateUser(@PathVariable int id,@RequestBody User user)
	{
		userService.updateUser(id,user);
		response = new Response();
		response.setStatus("done");
		System.out.println("controller");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable int id)
	{
		userService.deleteUser(id);
		response=new Response();
		response.setStatus("done");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
