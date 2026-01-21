package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Client;
import eus.fpsanturtzilh.pag.repository.ClientRepository;

@Service
public class ClientService {
	
	private final ClientRepository repository;
	
	public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

	public List<Client> getAllClients() {
        return repository.findAll();
    }

	public Client saveClient(Client client) {
		return repository.save(client);
	}

    public void deleteClient(Integer id) {
        repository.deleteById(id);
    }
    
    //Aldatu name, surname, phone, email eta home_client
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

	//Get Client bat
    public Client findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}