package eus.fpsanturtzilh.pag.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="students")
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@ManyToOne
	@JoinColumn(name="group_id", nullable = false)
	@JsonIgnore
	private GroupFroga groups;
	
	@Embedded
	private TimestampInfo info;
	
	@OneToOne(mappedBy = "students", cascade = CascadeType.ALL)
	@JsonIgnore
	private User users;

	@OneToMany (mappedBy="students", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Appointment> appoinments;
	
}

