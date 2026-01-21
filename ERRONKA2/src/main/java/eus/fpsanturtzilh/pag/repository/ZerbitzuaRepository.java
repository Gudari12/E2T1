package eus.fpsanturtzilh.pag.repository;

import eus.fpsanturtzilh.pag.model.Zerbitzua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZerbitzuaRepository extends JpaRepository<Zerbitzua, Integer> {
	
}
