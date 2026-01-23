package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Client;
import eus.fpsanturtzilh.pag.repository.ClientRepository;

/**
 * Client entitatea kudeatzen duen zerbitzua
 * Client-ak sortu, kontsultatu, eguneratu eta ezabatzeko eragiketak eskaintzen ditu
 */
@Service
public class ClientService {

    @Autowired
	private final ClientRepository repository;
	
	/**
	 * ClientService sortzailea
	 * 
	 * @param repository
	 */
	public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

	/**
	 * Client guztien zerrenda ikusteko
	 * 
	 * @return client guztien zerrenda
	 */
	public List<Client> getAllClients() {
        return repository.findAll();
    }

	/**
	 * Client berri bat sortzeko
	 * 
	 * @param client: sortu nahi den appointment
	 * @return gordetako client identifikatzailearekin
	 */
	public Client saveClient(Client client) {
		return repository.save(client);
	}

	/**
	 * Client espezifiko bat ezabatzeko
	 * 
	 * @param id: ezabatu nahi den client-aren identifikatzailea
	 */
    public void deleteClient(Integer id) {
        repository.deleteById(id);
    }
    
    /**
     * Client espezifiko baten izena, abizena, telefonoa, email eta home_client aldatzeko
     * 
     * @param id: eguneratu nahi den client-aren identifikatzailea
     * @param updateClient: eguneratzen diren balioak
     * @return eguneratutako client
     */
    public Client updateClient(Integer id, Client updateClient) {
    	Client existingClient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));

    	existingClient.setName(updateClient.getName());
    	existingClient.setSurname(updateClient.getSurname());
    	existingClient.setPhone(updateClient.getPhone());
    	existingClient.setEmail(updateClient.getEmail());
    	existingClient.setHome_client(updateClient.getHome_client());


        return repository.save(existingClient);
    }

    /**
     * Client espezifiko bat ikusteko
     * 
     * @param id: ikusi nahi den client-aren identifikatzailea
     * @return aurkitutako client edo null existitzen ez bada
     */
    public Client findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}