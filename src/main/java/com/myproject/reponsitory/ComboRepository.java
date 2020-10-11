package com.myproject.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.entity.Combo;

public interface ComboRepository extends JpaRepository<Combo, Integer> {

}
