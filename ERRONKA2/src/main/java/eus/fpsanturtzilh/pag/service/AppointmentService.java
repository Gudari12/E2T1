package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Appointment;
import eus.fpsanturtzilh.pag.repository.AppointmentRepository;

/**
 * Appointment entitatea kudeatzen duen zerbitzua
 * Appointment-ak sortu, kontsultatu, eguneratu eta ezabatzeko eragiketak eskaintzen ditu
 */
@Service
public class AppointmentService {

    @Autowired
	private final AppointmentRepository repository;

	/**
	 * AppointmentService sortzailea
	 * 
	 * @param repository
	 */
	public AppointmentService(AppointmentRepository repository) {
		this.repository = repository;
	}

	/**
	 * Appointment guztien zerrenda ikusteko
	 * 
	 * @return appointment guztien zerrenda
	 */
	public List<Appointment> getAllAppointments() {
		return repository.findAll();
	}

	/**
	 * Appointment berri bat sortzeko
	 * 
	 * @param appointment: sortu nahi den appointment
	 * @return gordetako appointment identifikatzailearekin
	 */
	public Appointment saveAppointment(Appointment appointment) {
		return repository.save(appointment);
	}

	/**
	 * Appointment espezifiko bat ezabatzeko
	 * 
	 * @param id: ezabatu nahi den appointment-aren identifikatzailea
	 */
    public void deleteAppointment(Integer id) {
        repository.deleteById(id);
    }
    
    /**
     * Appointment espezifiko baten lekua eta data aldatzeko
     * 
     * @param id: eguneratu nahi den appointment-aren identifikatzailea
     * @param updateAppointment: eguneratzen diren balioak
     * @return eguneratutako appointment
     */
    public Appointment updateAppointment(Integer id, Appointment updateAppointment) {
        Appointment existingAppointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));

        existingAppointment.setSeat(updateAppointment.getSeat());
        existingAppointment.setDate(updateAppointment.getDate());
        existingAppointment.setStart_time(updateAppointment.getStart_time());
        existingAppointment.setEnd_time(updateAppointment.getEnd_time());
        existingAppointment.setComment(updateAppointment.getComment());
        existingAppointment.setName(updateAppointment.getName());
        
        
        return repository.save(existingAppointment);
    }
    
    /**
     * Appointment espezifiko bat ikusteko
     * 
     * @param id: ikusi nahi den appointment-aren identifikatzailea
     * @return aurkitutako appointment edo null existitzen ez bada
     */
    public Appointment findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
