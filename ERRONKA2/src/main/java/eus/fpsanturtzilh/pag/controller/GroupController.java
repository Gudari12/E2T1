package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.GroupFroga;
import eus.fpsanturtzilh.pag.service.GroupService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
public class GroupController {

	@Autowired
	private final GroupService service;

	public GroupController(GroupService service) {
		this.service = service;
	}

	@GetMapping
	public List<GroupFroga> getGroups() {
		return service.getAllGroups();
	}
	
	@PostMapping
	public ResponseEntity<GroupFroga> saveGroupFroga(@RequestBody GroupFroga groupFroga) {
		GroupFroga saved = service.saveGroupFroga(groupFroga);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }	
	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteGroupFroga(@PathVariable Integer id) {
		service.deleteGroupFroga(id);
	}

    @PutMapping("/{id}")
    public GroupFroga updateGroupFroga(@PathVariable Integer id, @RequestBody GroupFroga groupFroga) {
        return service.updateGroupFroga(id, groupFroga);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<GroupFroga> findGroupFroga(@PathVariable Integer id){
		GroupFroga ap = service.findById(id);
		if (ap!=null) {
			return ResponseEntity.ok(ap);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}