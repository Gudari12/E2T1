package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.GroupFroga;
import eus.fpsanturtzilh.pag.repository.GroupRepository;

@Service
public class GroupService {
	
	private final GroupRepository repository;
	
	public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

	public List<GroupFroga> getAllGroups() {
        return repository.findAll();
    }

	public GroupFroga saveGroupFroga(GroupFroga groupFroga) {
		return repository.save(groupFroga);
	}

    public void deleteGroupFroga(Integer id) {
        repository.deleteById(id);
    }

    //Izena aldatu
    public GroupFroga updateGroupFroga(Integer id, GroupFroga updateGroupFroga) {
    	GroupFroga existingGroupFroga = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupFroga not found with id " + id));

    	existingGroupFroga.setName(updateGroupFroga.getName());


        return repository.save(existingGroupFroga);
    }

	//Get GroupFroga bat
    public GroupFroga findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}