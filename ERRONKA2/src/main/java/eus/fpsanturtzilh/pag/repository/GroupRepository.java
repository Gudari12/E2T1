package eus.fpsanturtzilh.pag.repository;

import eus.fpsanturtzilh.pag.model.GroupFroga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupFroga, Integer> {
	
}
