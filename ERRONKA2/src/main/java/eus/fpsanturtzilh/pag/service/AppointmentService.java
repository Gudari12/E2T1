package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Appointment;
import eus.fpsanturtzilh.pag.repository.AppointmentRepository;

@Service
public class AppointmentService {

	private final AppointmentRepository repository;

	public AppointmentService(AppointmentRepository repository) {
		this.repository = repository;
	}

	public List<Appointment> getAllAppointments() {
		return repository.findAll();
	}

	public Appointment saveAppointment(Appointment appointment) {
		return repository.save(appointment);
	}

    public void deleteAppointment(Integer id) {
        repository.deleteById(id);
    }
    
    //Aldatu lekua eta data
    public Appointment updateAppointment(Integer id, Appointment updateAppointment) {
        Appointment existingAppointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));

        existingAppointment.setSeat(updateAppointment.getSeat());
        existingAppointment.setDate(updateAppointment.getDate());

        return repository.save(existingAppointment);
    }
    
	//Get Appointment bat
    public Appointment findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
