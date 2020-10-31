package com.myproject.config;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailService;
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService)
			.passwordEncoder(new BCryptPasswordEncoder());	
		

	}
	@Autowired
    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.antMatcher("/**") //Kiểm tra với URL bắt đầu bằng /
		.authorizeRequests()//Phân quyền
		.antMatchers("/login","/","/service","/appointment","/blog/**","/about","/user/register","/api/appointment")
		.permitAll()
		.antMatchers("/admin/**")
		.hasAnyRole("ADMINISTRATOR","MANAGER")
		.anyRequest()//Mọi request gởi lên client bắt buộc đăng nhập
		.authenticated()//Như trên
		
		;
		
		http
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/success")
		.usernameParameter("email")
		.passwordParameter("password")
		.successHandler(authenticationSuccessHandler)
		.failureUrl("/login?error=true");
		
		
		
		http.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/");	
		http.exceptionHandling()
		.accessDeniedPage("/error/403");
	}
	
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .inMemoryAuthentication()
	                .withUser("user").password("password").roles("STYLIST","MEMBER")
	                .and()
	                .withUser("admin").password("password").roles("ADMINISTRATOR","MANAGER");
	    }
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**","/frontend/**","/backend/**","/upload/**");
	}
	
}
