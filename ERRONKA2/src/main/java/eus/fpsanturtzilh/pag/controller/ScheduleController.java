package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Schedule;
import eus.fpsanturtzilh.pag.service.ScheduleService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

	private final ScheduleService service;

	public ScheduleController(ScheduleService service) {
		this.service = service;
	}

	@GetMapping
	public List<Schedule> getSchedules() {
		return service.getAllSchedules();
	}
	
	@PostMapping
	public Schedule addSchedule(@RequestBody Schedule schedule) {
		return service.saveSchedule(schedule);
	}

	@DeleteMapping("/{id}")
	public void deleteSchedule(@PathVariable Integer id) {
		service.deleteSchedule(id);
	}

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Integer id, @RequestBody Schedule schedule) {
        return service.updateSchedule(id, schedule);
    }
	
	@GetMapping("/{id}")
	public Schedule getSchedule(@PathVariable Integer id) {
		return service.findById(id);
	}
}