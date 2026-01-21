package eus.fpsanturtzilh.pag.repository;

import eus.fpsanturtzilh.pag.model.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
}
