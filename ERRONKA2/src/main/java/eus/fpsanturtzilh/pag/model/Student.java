package eus.fpsanturtzilh.pag.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="students")
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@ManyToOne
	@JoinColumn(name="group_id", nullable = false)
	@JsonBackReference(value = "group-student")
	private GroupFroga groups;
	
	@Embedded
	private TimestampInfo info;
	
	@OneToOne(mappedBy = "students", cascade = CascadeType.ALL)
	private User users;

	@OneToMany (mappedBy="students", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "appoint-stud")
	private List<Appointment> appoinments;
	
}

