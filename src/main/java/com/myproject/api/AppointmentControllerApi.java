package com.myproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.dto.AppointmentDto;
import com.myproject.service.AppointmentService;

@RestController
@RequestMapping("/api")
public class AppointmentControllerApi {

	@Autowired
	private AppointmentService appServ;
	
	@PostMapping("/appointment")
	public ResponseEntity<Object> Object(@RequestBody AppointmentDto app, BindingResult bindingResult) {
		int result = appServ.add(app);
		if(result == 1 || bindingResult.hasErrors()) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		}
		
	}
}
