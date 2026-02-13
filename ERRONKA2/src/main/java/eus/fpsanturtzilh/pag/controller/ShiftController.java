package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Shift;
import eus.fpsanturtzilh.pag.service.ShiftService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shift")
@CrossOrigin(origins="*")
public class ShiftController {

	@Autowired
	private final ShiftService service;

	public ShiftController(ShiftService service) {
		this.service = service;
	}

	@GetMapping
	public List<Shift> getShifts() {
		return service.getAllShifts();
	}
	
	@PostMapping
	public ResponseEntity<Shift> saveShift(@RequestBody Shift shift) {
		Shift saved = service.saveShift(shift);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteShift(@PathVariable Integer id) {
		service.deleteShift(id);
	}

    @PutMapping("/{id}")
    public Shift updateShift(@PathVariable Integer id, @RequestBody Shift shift) {
        return service.updateShift(id, shift);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Shift> findShift(@PathVariable Integer id){
		Shift ap = service.findById(id);
		if (ap!=null) {
			return ResponseEntity.ok(ap);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}