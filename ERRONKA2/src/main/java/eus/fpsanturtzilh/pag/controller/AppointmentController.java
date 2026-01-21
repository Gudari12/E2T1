package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Appointment;
import eus.fpsanturtzilh.pag.service.AppointmentService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

	private final AppointmentService service;

	public AppointmentController(AppointmentService service) {
		this.service = service;
	}

	@GetMapping
	public List<Appointment> getAppointments() {
		return service.getAllAppointments();
	}
	
	@PostMapping
	public Appointment addAppointment(@RequestBody Appointment appointment) {
		return service.saveAppointment(appointment);
	}

	@DeleteMapping("/{id}")
	public void deleteAppointment(@PathVariable Integer id) {
		service.deleteAppointment(id);
	}
	
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointment) {
        return service.updateAppointment(id, appointment);
    }
	
	@GetMapping("/{id}")
	public Appointment getAppointment1(@PathVariable Integer id) {
		return service.findById(id);
	}
}