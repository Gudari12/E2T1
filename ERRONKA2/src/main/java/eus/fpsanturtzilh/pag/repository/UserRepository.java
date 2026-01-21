package eus.fpsanturtzilh.pag.repository;

import eus.fpsanturtzilh.pag.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
}
