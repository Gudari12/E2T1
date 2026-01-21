package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.GroupFroga;
import eus.fpsanturtzilh.pag.service.GroupService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
public class GroupController {

	private final GroupService service;

	public GroupController(GroupService service) {
		this.service = service;
	}

	@GetMapping
	public List<GroupFroga> getGroups() {
		return service.getAllGroups();
	}
	
	@PostMapping
	public GroupFroga addGroupFroga(@RequestBody GroupFroga groupFroga) {
		return service.saveGroupFroga(groupFroga);
	}

	@DeleteMapping("/{id}")
	public void deleteGroupFroga(@PathVariable Integer id) {
		service.deleteGroupFroga(id);
	}

    @PutMapping("/{id}")
    public GroupFroga updateGroupFroga(@PathVariable Integer id, @RequestBody GroupFroga groupFroga) {
        return service.updateGroupFroga(id, groupFroga);
    }
	
	@GetMapping("/{id}")
	public GroupFroga getGroupFroga(@PathVariable Integer id) {
		return service.findById(id);
	}
}