package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.User;
import eus.fpsanturtzilh.pag.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
        this.repository = repository;
    }

	public List<User> getAllUsers() {
        return repository.findAll();
    }

	public User saveUser(User user) {
		return repository.save(user);
	}

    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
    
    //Aldatu username, email eta rola
    public User updateUser(Integer id, User updateUser) {
    	User existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

    	existingUser.setUsername(updateUser.getUsername());
    	existingUser.setEmail(updateUser.getEmail());
    	existingUser.setRol(updateUser.getRol());

        return repository.save(existingUser);
    }

	//Get User bat
    public User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}