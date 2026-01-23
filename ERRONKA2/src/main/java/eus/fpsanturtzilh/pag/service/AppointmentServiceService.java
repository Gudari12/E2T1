package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.AppointmentService;
import eus.fpsanturtzilh.pag.repository.AppointmentServiceRepository;

/**
 * AppointmentService entitatea kudeatzen duen zerbitzua
 * AppointmentService-ak sortu, kontsultatu, eguneratu eta ezabatzeko eragiketak eskaintzen ditu
 */
@Service
public class AppointmentServiceService {

    @Autowired
	private final AppointmentServiceRepository repository;
	
	/**
	 * AppointmentServiceService sortzailea
	 * 
	 * @param repository
	 */
	public AppointmentServiceService(AppointmentServiceRepository repository) {
        this.repository = repository;
    }

	/**
	 * AppointmentService guztien zerrenda ikusteko
	 * 
	 * @return appointmentService guztien zerrenda
	 */
	public List<AppointmentService> getAllAppointmentServices() {
        return repository.findAll();
    }
	
	/**
	 * AppointmentService berri bat sortzeko
	 * 
	 * @param appointmentService: sortu nahi den appointmentService
	 * @return gordetako appointmentService identifikatzailearekin
	 */
	public AppointmentService saveAppointmentService(AppointmentService appointmentService) {
		return repository.save(appointmentService);
	}

	/**
	 * AppointmentService espezifiko bat ezabatzeko
	 * 
	 * @param id: ezabatu nahi den appointmentService-aren identifikatzailea
	 */
    public void deleteAppointmentService(Integer id) {
        repository.deleteById(id);
    }
    
    /**
     * AppointmentService espezifiko baten komentarioa aldatzeko
     * 
     * @param id: eguneratu nahi den appointmentService-aren identifikatzailea
     * @param updateAppointmentService: eguneratzen diren balioak
     * @return eguneratutako appointmentService
     */
    public AppointmentService updateAppointmentService(Integer id, AppointmentService updateAppointmentService) {
    	AppointmentService existingAppointmentService = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AppointmentService not found with id " + id));

        existingAppointmentService.setComment(updateAppointmentService.getComment());

        return repository.save(existingAppointmentService);
    }
    
    /**
     * AppointmentService espezifiko bat ikusteko
     * 
     * @param id: ikusi nahi den appointmentService-aren identifikatzailea
     * @return aurkitutako appointmentService edo null existitzen ez bada
     */
    public AppointmentService findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
