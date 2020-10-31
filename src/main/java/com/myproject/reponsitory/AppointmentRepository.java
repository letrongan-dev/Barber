package com.myproject.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myproject.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Query("SELECT a FROM Appointment a where a.phone = :phone ORDER BY a.id DESC")
	List<Appointment> listAppByPhone (String phone);
}
