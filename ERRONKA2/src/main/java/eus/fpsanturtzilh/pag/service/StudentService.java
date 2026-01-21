package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.Student;
import eus.fpsanturtzilh.pag.repository.StudentRepository;

@Service
public class StudentService {
	
	private final StudentRepository repository;
	
	public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

	public List<Student> getAllStudents() {
        return repository.findAll();
    }

	public Student saveStudent(Student student) {
		return repository.save(student);
	}

    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }
    
    //Aldatu name, surname, groups
    public Student updateStudent(Integer id, Student updateStudent) {
    	Student existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

    	existingStudent.setName(updateStudent.getName());
    	existingStudent.setSurname(updateStudent.getSurname());
    	existingStudent.setGroups(updateStudent.getGroups());

        return repository.save(existingStudent);
    }
    
	//Get Student bat
    public Student findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
