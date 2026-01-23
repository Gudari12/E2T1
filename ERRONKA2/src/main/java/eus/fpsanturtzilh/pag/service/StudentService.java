package eus.fpsanturtzilh.pag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eus.fpsanturtzilh.pag.model.GroupFroga;
import eus.fpsanturtzilh.pag.model.Student;
import eus.fpsanturtzilh.pag.repository.GroupRepository;
import eus.fpsanturtzilh.pag.repository.StudentRepository;

/**
 * Student entitatea kudeatzen duen zerbitzua
 * Student-ak sortu, kontsultatu, eguneratu eta ezabatzeko eragiketak eskaintzen ditu
 */
@Service
public class StudentService {

    @Autowired
	private final StudentRepository repository;
	private final GroupRepository groupRepository;
	
	/**
	 * StudentService sortzailea
	 * 
	 * @param repository
	 */
	public StudentService(StudentRepository repository, GroupRepository groupRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
    }

	/**
	 * Student guztien zerrenda ikusteko
	 * 
	 * @return student guztien zerrenda
	 */
	public List<Student> getAllStudents() {
        return repository.findAll();
    }

	/**
	 * Student berri bat sortzeko
	 * 
	 * @param student: sortu nahi den appointment
	 * @return gordetako student identifikatzailearekin
	 */
	public Student saveStudent(Student student) {
        Integer groupId = student.getGroups().getId();
        GroupFroga group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con id: " + groupId));

        student.setGroups(group);
		return repository.save(student);
	}

	/**
	 * Student espezifiko bat ezabatzeko
	 * 
	 * @param id: ezabatu nahi den student-aren identifikatzailea
	 */
    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }
    
    /**
     * Student espezifiko baten izena, abizena eta taldea aldatzeko
     * 
     * @param id: eguneratu nahi den student-aren identifikatzailea
     * @param updateStudent: eguneratzen diren balioak
     * @return eguneratutako student
     */
    public Student updateStudent(Integer id, Student updateStudent) {
    	Student existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

    	existingStudent.setName(updateStudent.getName());
    	existingStudent.setSurname(updateStudent.getSurname());
    	
        Integer groupId = updateStudent.getGroups().getId();
        GroupFroga group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con id: " + groupId));
        existingStudent.setGroups(group);
    	existingStudent.setGroups(updateStudent.getGroups());

        return repository.save(existingStudent);
    }
    
    /**
     * Student espezifiko bat ikusteko
     * 
     * @param id: ikusi nahi den student-aren identifikatzailea
     * @return aurkitutako student edo null existitzen ez bada
     */
    public Student findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
