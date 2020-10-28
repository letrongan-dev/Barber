package com.myproject.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myproject.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>  {

	@Query("SELECT b FROM Bill b where b.status = ?1")
	List<Bill> billUnpaid(int status);
}
