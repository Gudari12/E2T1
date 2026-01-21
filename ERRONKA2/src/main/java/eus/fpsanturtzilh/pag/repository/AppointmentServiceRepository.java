package eus.fpsanturtzilh.pag.repository;

import eus.fpsanturtzilh.pag.model.AppointmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentServiceRepository extends JpaRepository<AppointmentService, Integer> {

	
}