package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Client;
import eus.fpsanturtzilh.pag.service.ClientService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

	private final ClientService service;

	public ClientController(ClientService service) {
		this.service = service;
	}

	@GetMapping
	public List<Client> getClients() {
		return service.getAllClients();
	}
	
	@PostMapping
	public Client addClient(@RequestBody Client client) {
		return service.saveClient(client);
	}

	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable Integer id) {
		service.deleteClient(id);
	}

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Integer id, @RequestBody Client client) {
        return service.updateClient(id, client);
    }
	
	@GetMapping("/{id}")
	public Client getClient(@PathVariable Integer id) {
		return service.findById(id);
	}
}