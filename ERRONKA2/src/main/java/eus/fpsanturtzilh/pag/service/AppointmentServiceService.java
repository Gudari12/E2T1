package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.AppointmentService;
import eus.fpsanturtzilh.pag.repository.AppointmentServiceRepository;

@Service
public class AppointmentServiceService {
	
	private final AppointmentServiceRepository repository;
	
	public AppointmentServiceService(AppointmentServiceRepository repository) {
        this.repository = repository;
    }

	public List<AppointmentService> getAllAppointmentServices() {
        return repository.findAll();
    }

	public AppointmentService saveAppointmentService(AppointmentService appointmentService) {
		return repository.save(appointmentService);
	}

    public void deleteAppointmentService(Integer id) {
        repository.deleteById(id);
    }
    
    //Comentarioa aldatu
    public AppointmentService updateAppointmentService(Integer id, AppointmentService updateAppointmentService) {
    	AppointmentService existingAppointmentService = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AppointmentService not found with id " + id));

        existingAppointmentService.setComment(updateAppointmentService.getComment());

        return repository.save(existingAppointmentService);
    }
    
	//Get AppointmentService bat
    public AppointmentService findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
