package com.myproject.config;

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

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService)
			.passwordEncoder(new BCryptPasswordEncoder());	
		

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.antMatcher("/**") //Kiểm tra với URL bắt đầu bằng /
		.authorizeRequests()//Phân quyền
		.antMatchers("/login","/","/service","/appointment","/blog/**","/about")
		.permitAll()
		.antMatchers("/admin/**")
		.hasAnyRole("ADMINISTRATOR","MANAGER")
		.anyRequest()//Mọi request gởi lên client bắt buộc đăng nhập
		.authenticated()//Như trên
		
		;
		
		http.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/login")
		.usernameParameter("email")
		.passwordParameter("password")
		.defaultSuccessUrl("/admin/dashboard")
		.failureUrl("/login?error=true");
		
		
		http.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login")
		.deleteCookies("JSESSIONID");
		
		http.exceptionHandling()
		.accessDeniedPage("/error/403");
	}
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService());
//		authProvider.setPasswordEncoder(passwordEncoder());
//		
//		return authProvider;
//	}
//
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/admin**").hasRole("ADMINISTRATOR")
//			.anyRequest().authenticated()
//			.and()
//			.formLogin().permitAll()
//			.and()
//			.logout()
//				.permitAll()
//				.logoutUrl("/logout")
//				.logoutSuccessUrl("/login")
//			.and().csrf().disable()
//			.exceptionHandling().accessDeniedPage("/error/403")
//			;
//		http.formLogin()
//		.loginPage("/login")
//		.loginProcessingUrl("/login")
//		.usernameParameter("email")
//		.passwordParameter("password")
//		.defaultSuccessUrl("/admin/dashboard")
//		.failureUrl("/login?error=true");
//	}
//	
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**","/frontend/**","/backend/**","/upload/**");
	}
	
}
