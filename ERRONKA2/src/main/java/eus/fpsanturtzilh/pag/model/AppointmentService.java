package eus.fpsanturtzilh.pag.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

/**
 * AppointmentService entitatea
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="appointments_services")
public class AppointmentService implements Serializable{

	private static final long serialVersionUID = 1L;
	
    /**
     * Appointment-aren identifikatzaile bakarra
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    /**
     * AppointmentService bakoitza appointment bakar bati dagokio
     * Entitate honek Appointment eta Zerbitzua arteko erlazioa adierazten du
     */
	@ManyToOne
	@JoinColumn(name = "appointment_id", nullable = false)
	@JsonBackReference(value = "appoint-app_serv")
	private Appointment appointments;

    /**
     * AppointmentService bakoitza service bakar bati dagokio
     * Entitate honek Appointment eta Zerbitzua arteko erlazioa adierazten du
     */
	@ManyToOne
	@JoinColumn(name="service_id", nullable = false)
	@JsonBackReference(value = "app_serv-zerb")
	private Zerbitzua services;

    /**
     * AppointmentService komentario laburra
     */
	@Column
	private String comment;

    /**
     * Erregistroaren sortze, eguneratze edo ezabatze informazioa
     */
	@Embedded
	private TimestampInfo timestamps = new TimestampInfo();
	
}