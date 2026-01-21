package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Student;
import eus.fpsanturtzilh.pag.service.StudentService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	private final StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}

	@GetMapping
	public List<Student> getStudents() {
		return service.getAllStudents();
	}
	
	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		return service.saveStudent(student);
	}

	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		service.deleteStudent(id);
	}

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        return service.updateStudent(id, student);
    }
	
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable Integer id) {
		return service.findById(id);
	}
}