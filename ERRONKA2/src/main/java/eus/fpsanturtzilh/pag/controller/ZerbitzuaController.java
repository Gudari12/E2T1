package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Zerbitzua;
import eus.fpsanturtzilh.pag.service.ZerbitzuaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zerbitzua")
public class ZerbitzuaController {

	@Autowired
	private final ZerbitzuaService service;

	public ZerbitzuaController(ZerbitzuaService service) {
		this.service = service;
	}

	@GetMapping
	public List<Zerbitzua> getZerbitzuak() {
		return service.getAllZerbitzuak();
	}
	
	@PostMapping
	public ResponseEntity<Zerbitzua> saveZerbitzua(@RequestBody Zerbitzua zerbitzua) {
		Zerbitzua saved = service.saveZerbitzua(zerbitzua);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }	

	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteZerbitzua(@PathVariable Integer id) {
		service.deleteZerbitzua(id);
	}

    @PutMapping("/{id}")
    public Zerbitzua updateZerbitzua(@PathVariable Integer id, @RequestBody Zerbitzua zerbitzua) {
        return service.updateZerbitzua(id, zerbitzua);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Zerbitzua> findZerbitzua(@PathVariable Integer id){
		Zerbitzua ap = service.findById(id);
		if (ap!=null) {
			return ResponseEntity.ok(ap);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}