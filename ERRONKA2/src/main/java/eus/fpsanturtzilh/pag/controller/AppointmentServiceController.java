package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.AppointmentService;
import eus.fpsanturtzilh.pag.service.AppointmentServiceService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment_service")
public class AppointmentServiceController {

	private final AppointmentServiceService service;

	public AppointmentServiceController(AppointmentServiceService service) {
		this.service = service;
	}

	@GetMapping
	public List<AppointmentService> getAppointmentServices() {
		return service.getAllAppointmentServices();
	}
	
	@PostMapping
	public AppointmentService addAppointmentService(@RequestBody AppointmentService appointmentService) {
		return service.saveAppointmentService(appointmentService);
	}

	@DeleteMapping("/{id}")
	public void deleteAppointmentService(@PathVariable Integer id) {
		service.deleteAppointmentService(id);
	}
	
    @PutMapping("/{id}")
    public AppointmentService updateAppointmentService(@PathVariable Integer id, @RequestBody AppointmentService appointmentService) {
        return service.updateAppointmentService(id, appointmentService);
    }
	
	@GetMapping("/{id}")
	public AppointmentService getAppointmentService(@PathVariable Integer id) {
		return service.findById(id);
	}
}