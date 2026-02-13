package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Schedule;
import eus.fpsanturtzilh.pag.service.ScheduleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins="*")
public class ScheduleController {

	@Autowired
	private final ScheduleService service;

	public ScheduleController(ScheduleService service) {
		this.service = service;
	}

	@GetMapping
	public List<Schedule> getSchedules() {
		return service.getAllSchedules();
	}
	
	@PostMapping
	public ResponseEntity<Schedule> saveSchedule(@RequestBody Schedule schedule) {
		Schedule saved = service.saveSchedule(schedule);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }	

	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteSchedule(@PathVariable Integer id) {
		service.deleteSchedule(id);
	}

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Integer id, @RequestBody Schedule schedule) {
        return service.updateSchedule(id, schedule);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Schedule> findSchedule(@PathVariable Integer id){
		Schedule ap = service.findById(id);
		if (ap!=null) {
			return ResponseEntity.ok(ap);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}