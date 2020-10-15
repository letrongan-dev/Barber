//package com.myproject.dto;
//
//import java.util.*;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.myproject.entity.Role;
//import com.myproject.entity.User;
// 
//public class ShowUserDetail implements UserDetails {
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	private UserDto user;
//
//     
//    public String getFullname() {
//		return this.user.getName();
//	}
//
//	public String getAvatar() {
//		return this.user.getAvatar();
//	}
//
//
//	public ShowUserDetail(UserDto user) {
//        this.user = user;
//    }
//  
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
// 
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
// 
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
// 
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
// 
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//	
//}
