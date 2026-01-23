package eus.fpsanturtzilh.pag.controller;

import eus.fpsanturtzilh.pag.model.Student;
import eus.fpsanturtzilh.pag.service.StudentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private final StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}

	@GetMapping
	public List<Student> getStudents() {
		return service.getAllStudents();
	}
	
	@PostMapping
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		Student saved = service.saveStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }	

	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteStudent(@PathVariable Integer id) {
		service.deleteStudent(id);
	}

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        return service.updateStudent(id, student);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> findStudent(@PathVariable Integer id){
		Student ap = service.findById(id);
		if (ap!=null) {
			return ResponseEntity.ok(ap);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}