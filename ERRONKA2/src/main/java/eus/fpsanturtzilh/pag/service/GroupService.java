package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.GroupFroga;
import eus.fpsanturtzilh.pag.repository.GroupRepository;

/**
 * GroupFroga entitatea kudeatzen duen zerbitzua
 * GroupFroga-ak sortu, kontsultatu, eguneratu eta ezabatzeko eragiketak eskaintzen ditu
 */
@Service
public class GroupService {

    @Autowired
	private final GroupRepository repository;

	/**
	 * GroupService sortzailea
	 * 
	 * @param repository
	 */
	public GroupService(GroupRepository repository) {
        this.repository = repository;
    }

	/**
	 * GroupFroga guztien zerrenda ikusteko
	 * 
	 * @return groupFroga guztien zerrenda
	 */
	public List<GroupFroga> getAllGroups() {
        return repository.findAll();
    }

	/**
	 * GroupFroga berri bat sortzeko
	 * 
	 * @param groupFroga: sortu nahi den groupFroga
	 * @return gordetako groupFroga identifikatzailearekin
	 */
	public GroupFroga saveGroupFroga(GroupFroga groupFroga) {
		return repository.save(groupFroga);
	}

	/**
	 * GroupFroga espezifiko bat ezabatzeko
	 * 
	 * @param id: ezabatu nahi den groupFroga-aren identifikatzailea
	 */
    public void deleteGroupFroga(Integer id) {
        repository.deleteById(id);
    }

    /**
     * GroupFroga espezifiko baten izena aldatzeko
     * 
     * @param id: eguneratu nahi den groupFroga-aren identifikatzailea
     * @param updateGroupFroga: eguneratzen diren balioak
     * @return eguneratutako groupFroga
     */
    public GroupFroga updateGroupFroga(Integer id, GroupFroga updateGroupFroga) {
    	GroupFroga existingGroupFroga = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupFroga not found with id " + id));

    	existingGroupFroga.setName(updateGroupFroga.getName());


        return repository.save(existingGroupFroga);
    }

    /**
     * GroupFroga espezifiko bat ikusteko
     * 
     * @param id: ikusi nahi den groupFroga-aren identifikatzailea
     * @return aurkitutako groupFroga edo null existitzen ez bada
     */
    public GroupFroga findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}