package eus.fpsanturtzilh.pag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="appointments_services")
public class AppointmentService {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	@ManyToOne
	@JoinColumn(name = "appointment_id", nullable = false)
	@JsonIgnore
	private Appointment appointments;
	
	@ManyToOne
	@JoinColumn(name="service_id", nullable = false)
	@JsonIgnore
	private Zerbitzua services;
	
	@Column
	private String comment;

	@Embedded
	private TimestampInfo info;
	
}