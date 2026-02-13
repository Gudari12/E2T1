package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.AppointmentService;
import eus.fpsanturtzilh.pag.service.AppointmentServiceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment_service")
@CrossOrigin(origins="*")
public class AppointmentServiceController {

	@Autowired
	private final AppointmentServiceService service;

	public AppointmentServiceController(AppointmentServiceService service) {
		this.service = service;
	}

	@GetMapping
	public List<AppointmentService> getAppointmentServices() {
		return service.getAllAppointmentServices();
	}
	
	@PostMapping
	public ResponseEntity<AppointmentService> saveAppointmentService(@RequestBody AppointmentService appointmentService) {
		AppointmentService saved = service.saveAppointmentService(appointmentService);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }	

	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteAppointmentService(@PathVariable Integer id) {
		service.deleteAppointmentService(id);
	}
	
    @PutMapping("/{id}")
    public AppointmentService updateAppointmentService(@PathVariable Integer id, @RequestBody AppointmentService appointmentService) {
        return service.updateAppointmentService(id, appointmentService);
    }
	
    @GetMapping("/{id}")
	public ResponseEntity<AppointmentService> findAppointmentService(@PathVariable Integer id){
    	AppointmentService ap = service.findById(id);
		if (ap!=null) {
			return ResponseEntity.ok(ap);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}