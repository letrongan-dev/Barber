package com.myproject.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myproject.dto.AuthChangePasswordDto;
import com.myproject.dto.UserDto;
import com.myproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
		
	@Query("SELECT new com.myproject.dto.UserDto(u.id, u.code, u.name, u.phone, u.email, u.password, u.address, u.roleId, u.avatar, r.name) FROM User u JOIN Role r ON u.roleId = r.id WHERE u.email = :email")
	UserDto findByEmailDto(@Param("email") String email);
	
	int countByEmail(String email);
	
	@Query("SELECT u FROM User u JOIN Role r ON u.roleId = r.id WHERE u.roleId = :id")
	List<User> listStylist(int id);
	
	@Query("SELECT u FROM User u JOIN Role r ON u.roleId = r.id WHERE u.roleId = :idST OR u.roleId = :idCus")
	List<User> listStylistAndCus(int idST, int idCus);
	
	User findByCode(String code);
	
	User findByEmail(String email);
	
	@Query("SELECT new com.myproject.dto.AuthChangePasswordDto(u.code, u.password) from User u WHERE u.code = :code")
	AuthChangePasswordDto authChangePass (@Param("code") String code);
}
