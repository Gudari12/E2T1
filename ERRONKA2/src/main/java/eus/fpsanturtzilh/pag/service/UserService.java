package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.User;
import eus.fpsanturtzilh.pag.repository.UserRepository;

/**
 * User entitatea kudeatzen duen zerbitzua
 * User-ak sortu, kontsultatu, eguneratu eta ezabatzeko eragiketak eskaintzen ditu
 */
@Service
public class UserService {

    @Autowired
	private final UserRepository repository;

	/**
	 * UserService sortzailea
	 * 
	 * @param repository
	 */
	public UserService(UserRepository repository) {
        this.repository = repository;
    }

	/**
	 * User guztien zerrenda ikusteko
	 * 
	 * @return user guztien zerrenda
	 */
	public List<User> getAllUsers() {
        return repository.findAll();
    }

	/**
	 * User berri bat sortzeko
	 * 
	 * @param user: sortu nahi den appointment
	 * @return gordetako user identifikatzailearekin
	 */
	public User saveUser(User user) {
		return repository.save(user);
	}

	/**
	 * User espezifiko bat ezabatzeko
	 * 
	 * @param id: ezabatu nahi den user-aren identifikatzailea
	 */
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }
    
    /**
     * User espezifiko baten erabiltzaile_izena, email eta rola aldatzeko
     * 
     * @param id: eguneratu nahi den user-aren identifikatzailea
     * @param updateUser: eguneratzen diren balioak
     * @return eguneratutako user
     */
    public User updateUser(Integer id, User updateUser) {
    	User existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

    	existingUser.setUsername(updateUser.getUsername());
    	existingUser.setEmail(updateUser.getEmail());
    	existingUser.setRol(updateUser.getRol());

        return repository.save(existingUser);
    }

    /**
     * User espezifiko bat ikusteko
     * 
     * @param id: ikusi nahi den user-aren identifikatzailea
     * @return aurkitutako user edo null existitzen ez bada
     */
    public User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}