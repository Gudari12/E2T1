package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Schedule;
import eus.fpsanturtzilh.pag.repository.ScheduleRepository;

/**
 * Schedule entitatea kudeatzen duen zerbitzua
 * Schedule-ak sortu, kontsultatu, eguneratu eta ezabatzeko eragiketak eskaintzen ditu
 */
@Service
public class ScheduleService {

    @Autowired
	private final ScheduleRepository repository;

	/**
	 * ScheduleService sortzailea
	 * 
	 * @param repository
	 */
	public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

	/**
	 * Schedule guztien zerrenda ikusteko
	 * 
	 * @return schedule guztien zerrenda
	 */
	public List<Schedule> getAllSchedules() {
        return repository.findAll();
    }

	/**
	 * Schedule berri bat sortzeko
	 * 
	 * @param schedule: sortu nahi den appointment
	 * @return gordetako schedule identifikatzailearekin
	 */
	public Schedule saveSchedule(Schedule schedule) {
		return repository.save(schedule);
	}

	/**
	 * Schedule espezifiko bat ezabatzeko
	 * 
	 * @param id: ezabatu nahi den schedule-aren identifikatzailea
	 */
    public void deleteSchedule(Integer id) {
        repository.deleteById(id);
    }
    
    /**
     * Schedule espezifiko baten eguna, hasiera/amaiera data/denbora aldatzeko
     * 
     * @param id: eguneratu nahi den schedule-aren identifikatzailea
     * @param updateSchedule: eguneratzen diren balioak
     * @return eguneratutako schedule
     */
    public Schedule updateSchedule(Integer id, Schedule updateSchedule) {
    	Schedule existingSchedule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + id));

    	existingSchedule.setDay(updateSchedule.getDay());
    	existingSchedule.setStart_date(updateSchedule.getStart_date());
    	existingSchedule.setEnd_date(updateSchedule.getEnd_date());
    	existingSchedule.setStart_time(updateSchedule.getStart_time());
    	existingSchedule.setEnd_time(updateSchedule.getEnd_time());

        return repository.save(existingSchedule);
    }

    /**
     * Schedule espezifiko bat ikusteko
     * 
     * @param id: ikusi nahi den schedule-aren identifikatzailea
     * @return aurkitutako schedule edo null existitzen ez bada
     */
    public Schedule findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}