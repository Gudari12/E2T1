package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Schedule;
import eus.fpsanturtzilh.pag.repository.ScheduleRepository;

@Service
public class ScheduleService {
	
	private final ScheduleRepository repository;
	
	public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

	public List<Schedule> getAllSchedules() {
        return repository.findAll();
    }

	public Schedule saveSchedule(Schedule schedule) {
		return repository.save(schedule);
	}

    public void deleteSchedule(Integer id) {
        repository.deleteById(id);
    }
    
    //Day, start/end date/time aldatu
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

	//Get Schedule bat
    public Schedule findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}