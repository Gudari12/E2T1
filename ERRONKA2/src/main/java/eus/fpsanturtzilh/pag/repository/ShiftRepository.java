package eus.fpsanturtzilh.pag.repository;

import eus.fpsanturtzilh.pag.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {
	
}
