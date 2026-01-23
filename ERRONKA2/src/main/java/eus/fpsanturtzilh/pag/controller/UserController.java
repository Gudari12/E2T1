package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.User;
import eus.fpsanturtzilh.pag.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public List<User> getUsers() {
		return service.getAllUsers();
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User saved = service.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Integer id) {
		service.deleteUser(id);
	}

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return service.updateUser(id, user);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findUser(@PathVariable Integer id){
		User ap = service.findById(id);
		if (ap!=null) {
			return ResponseEntity.ok(ap);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}