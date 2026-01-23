package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Client;
import eus.fpsanturtzilh.pag.service.ClientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

	@Autowired
	private final ClientService service;

	public ClientController(ClientService service) {
		this.service = service;
	}

	@GetMapping
	public List<Client> getClients() {
		return service.getAllClients();
	}
	
	@PostMapping
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		Client saved = service.saveClient(client);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }	

	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteClient(@PathVariable Integer id) {
		service.deleteClient(id);
	}

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Integer id, @RequestBody Client client) {
        return service.updateClient(id, client);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findClient(@PathVariable Integer id){
		Client ap = service.findById(id);
		if (ap!=null) {
			return ResponseEntity.ok(ap);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}