package com.myproject.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
