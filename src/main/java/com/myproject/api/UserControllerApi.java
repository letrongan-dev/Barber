package com.myproject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.dto.AuthChangePasswordDto;
import com.myproject.dto.UserDto;
import com.myproject.reponsitory.UserRepository;
import com.myproject.service.UserService;

@RestController
public class UserControllerApi {

	@Autowired
	private UserService userSer;
	
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping(value = "/api/register")
	public ResponseEntity<Object> Object(@RequestBody UserDto userDto, BindingResult bindingResult) {
		int checkEmail = userSer.checkExist(userDto.getEmail());
		if(bindingResult.hasErrors() || checkEmail>0) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			userSer.addCustomer(userDto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		}
	}
	@PutMapping(value = "/api/user/changepass")
	public ResponseEntity<Object> Object(@RequestBody AuthChangePasswordDto dto){
		int result = userSer.changePass(dto);
		AuthChangePasswordDto auth = userRepository.authChangePass(dto.getCode());
		System.err.println(result);
		if(auth == null) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		if(result != 1) {
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
}
