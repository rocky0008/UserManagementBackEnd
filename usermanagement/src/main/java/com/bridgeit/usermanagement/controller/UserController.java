package com.bridgeit.usermanagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.usermanagement.dto.CountUser;
import com.bridgeit.usermanagement.dto.UserDto;
import com.bridgeit.usermanagement.dto.UserListDto;
import com.bridgeit.usermanagement.model.Response;
import com.bridgeit.usermanagement.model.ResponseToken;
import com.bridgeit.usermanagement.model.User;
import com.bridgeit.usermanagement.model.UserLogin;
import com.bridgeit.usermanagement.service.IUserService;

@RestController
@CrossOrigin(origins = {})
public class UserController {

	Response response;
	ResponseToken tokenResponse;
//	@Autowired
//	UserDto userDto;
	@Autowired
	IUserService userService;

	@RequestMapping("/")
	public String welcome() {
		System.out.println(userService);
		return "Welcome";
	}

//	@RequestMapping(value="/login" ,method=RequestMethod.POST)
	@PostMapping("/login")
	public ResponseEntity<ResponseToken> loginUser(@RequestBody UserDto userInfo, HttpServletResponse header)

	{
		String token=userService.validateUser(userInfo);
		tokenResponse = new ResponseToken();
		if (token!=null) {
			tokenResponse.setStatus("done");
			tokenResponse.setToken(token);
			return new ResponseEntity<ResponseToken>(tokenResponse, HttpStatus.OK);
		}

		else {
			tokenResponse.setStatus("not valid");
			tokenResponse.setToken("null");
			return new ResponseEntity<ResponseToken>(tokenResponse, HttpStatus.NOT_FOUND);
		}

	}
	@PostMapping("/forgotPassword")
	public ResponseEntity<Response> forgotPassword(@RequestParam("email") String email)
	{
		boolean check=userService.sendUserNameByEmail(email);
		response = new Response();
		if(check)
		{
			System.out.println("hihihi");
			response.setStatus("Your password has been send again to your Registered Email");
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		}
		response.setStatus("You have to create Account");
		return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);

	}
	
	
	@PostMapping("/user")
	public ResponseEntity<Response> addUser(@RequestBody User user)
	{
		System.out.println(user);
		userService.createUser(user);
		response=new Response();
		response.setStatus("done");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}	
	
	@GetMapping("/userList")
	public ResponseEntity<List<UserListDto>> getAllUser()
	{
		List<UserListDto> userList=userService.getAllUser();
		response = new Response();
		response.setStatus("done");
		System.out.println("hihihi");
		return new ResponseEntity<List<UserListDto>>(userList,HttpStatus.OK);
	}
	
	@GetMapping("/user/{token:.+}")
	public ResponseEntity<User> getUser(@PathVariable String token)
	{
		System.out.println(token);
		User user=userService.getUser(token);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	 
	@PutMapping("/user/{token:.+}")
	public ResponseEntity<Response> updateUser(@PathVariable String token,@RequestBody User user)
	{
		userService.updateUser(token,user);
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
	
	@GetMapping("/userCount/{token:.+}")
	public ResponseEntity<CountUser> countUsers(@PathVariable String token)
	{
		System.out.println("count");
		CountUser countUser=new CountUser(); 
		countUser=userService.getCount(token);
		System.out.println(countUser);
		return new ResponseEntity<CountUser>(countUser,HttpStatus.OK);
	}
	
	@GetMapping("/userLogin/{token:.+}")
	public ResponseEntity<List<UserLogin>> getLoginTime(@PathVariable String token)
	{
		System.out.println("usrLogin");
		List<UserLogin> userLoginTime = userService.getUserLogin(token);
		return new ResponseEntity<List<UserLogin>>(userLoginTime,HttpStatus.OK);
		 
	}
}
