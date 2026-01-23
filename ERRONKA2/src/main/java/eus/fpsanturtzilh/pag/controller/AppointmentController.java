package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Appointment;
import eus.fpsanturtzilh.pag.service.AppointmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Appointment baliabidearen REST kontrolatzailea
 */
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

	@Autowired
    private final AppointmentService service;

    /**
     * AppointmentController sortzailea
     *
     * @param service
     */
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    /**
     * Appointment guztien zerrenda lortzen du
     *
     * @return erregistratutako appointment guztien zerrenda
     */
    @GetMapping
    public List<Appointment> getAppointments() {
        return service.getAllAppointments();
    }
    
    /**
     * Appointment berri bat sortzen du
     * 
     * @param appointment: sortu nahi den appointment-aren datuak
     * @return sortutako appointment
     */
	@PostMapping
	public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
		Appointment saved = service.saveAppointment(appointment);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }	

    /**
     * Identifikatzailearen bidez appointment espezifiko bat ezabatzen du
     *
     * @param id: ezabatu nahi den appointment-aren identifikatzailea
     */
	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteAppointment(@PathVariable Integer id) {
		service.deleteAppointment(id);
	}
    
    /**
     * Appointment baten datuak eguneratzen ditu
     *
     * @param id: eguneratu nahi den appointment-aren identifikatzailea
     * @param appointment: datu berriak dituen appointment
     * @return eguneratutako appointment
     */
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointment) {
        return service.updateAppointment(id, appointment);
    }
  
    /**
     * Identifikatzailearen bidez appointment espezifiko bat lortzen du
     * 
     * @param id: bilatu nahi den appointment-aren identifikatzailea
     * @return aurkitutako appointment edo null existitzen ez bada
     */
	@GetMapping("/{id}")
	public ResponseEntity<Appointment> findAppointment(@PathVariable Integer id){
		Appointment ap = service.findById(id);
		if (ap!=null) {
			return ResponseEntity.ok(ap);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}