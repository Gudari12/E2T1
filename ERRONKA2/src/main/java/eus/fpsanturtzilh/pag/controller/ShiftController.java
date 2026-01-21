package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Shift;
import eus.fpsanturtzilh.pag.service.ShiftService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shift")
public class ShiftController {

	private final ShiftService service;

	public ShiftController(ShiftService service) {
		this.service = service;
	}

	@GetMapping
	public List<Shift> getShifts() {
		return service.getAllShifts();
	}
	
	@PostMapping
	public Shift addShift(@RequestBody Shift shift) {
		return service.saveShift(shift);
	}

	@DeleteMapping("/{id}")
	public void deleteShift(@PathVariable Integer id) {
		service.deleteShift(id);
	}

    @PutMapping("/{id}")
    public Shift updateShift(@PathVariable Integer id, @RequestBody Shift shift) {
        return service.updateShift(id, shift);
    }
	
	@GetMapping("/{id}")
	public Shift getShift(@PathVariable Integer id) {
		return service.findById(id);
	}
}