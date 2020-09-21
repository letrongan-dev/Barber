package com.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myproject.impl.RoleServiceImpl;
import com.myproject.service.RoleService;



@Configuration
public class AppConfig {

	@Bean
	public RoleService roleService() {
		return new RoleServiceImpl();
	};
}
