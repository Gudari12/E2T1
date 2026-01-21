package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.User;
import eus.fpsanturtzilh.pag.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public List<User> getUsers() {
		return service.getAllUsers();
	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		service.deleteUser(id);
	}

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return service.updateUser(id, user);
    }
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable Integer id) {
		return service.findById(id);
	}
}