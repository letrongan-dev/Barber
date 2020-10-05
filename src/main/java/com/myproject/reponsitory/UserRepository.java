package com.myproject.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myproject.dto.UserDto;
import com.myproject.dto.UserDtoUpdate;
import com.myproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT new com.myproject.dto.UserDtoUpdate(u.code, u.name, u.email, u.address, u.phone, u.roleId, u.avatar, r.name) FROM User u JOIN Role r ON u.roleId = r.id WHERE u.code = :code")
	UserDtoUpdate findByCodeDto(@Param("code") String code);
}
