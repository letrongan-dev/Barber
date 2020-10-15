package com.myproject.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myproject.dto.UserDto;
import com.myproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
		
	@Query("SELECT new com.myproject.dto.UserDto(u.id, u.code, u.name, u.phone, u.email, u.password, u.address, u.roleId, u.avatar, r.name) FROM User u JOIN Role r ON u.roleId = r.id WHERE u.email = :email")
	UserDto findByEmailDto(@Param("email") String email);
	
	int countByEmail(String email);
}
