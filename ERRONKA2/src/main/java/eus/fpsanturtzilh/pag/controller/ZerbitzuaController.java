package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Zerbitzua;
import eus.fpsanturtzilh.pag.service.ZerbitzuaService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zerbitzua")
public class ZerbitzuaController {

	private final ZerbitzuaService service;

	public ZerbitzuaController(ZerbitzuaService service) {
		this.service = service;
	}

	@GetMapping
	public List<Zerbitzua> getZerbitzuak() {
		return service.getAllZerbitzuak();
	}
	
	@PostMapping
	public Zerbitzua addZerbitzua(@RequestBody Zerbitzua zerbitzua) {
		return service.saveZerbitzua(zerbitzua);
	}

	@DeleteMapping("/{id}")
	public void deleteZerbitzua(@PathVariable Integer id) {
		service.deleteZerbitzua(id);
	}

    @PutMapping("/{id}")
    public Zerbitzua updateZerbitzua(@PathVariable Integer id, @RequestBody Zerbitzua zerbitzua) {
        return service.updateZerbitzua(id, zerbitzua);
    }
	
	@GetMapping("/{id}")
	public Zerbitzua getZerbitzua(@PathVariable Integer id) {
		return service.findById(id);
	}
	
}