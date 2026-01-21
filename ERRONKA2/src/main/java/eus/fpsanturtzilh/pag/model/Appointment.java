package eus.fpsanturtzilh.pag.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="appointments")
public class Appointment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private int seat;

	@Column
	private LocalDate date;
	
	@Column
	private LocalTime start_time;
	
	@Column
	private LocalTime end_time;
	
	@Column
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable = false)
	@JsonIgnore
	private Student students;
	
	@ManyToOne
	@JoinColumn(name="client_id", nullable = false)
	@JsonIgnore
	private Client clients;
		
	@Column
	private String name;

	@Embedded
	private TimestampInfo info;

	@OneToMany (mappedBy="appointments", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<AppointmentService> appoinments_services;
	
}
