package com.myproject.reponsitory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findById(int id);
	Role findByName(String name);
}
