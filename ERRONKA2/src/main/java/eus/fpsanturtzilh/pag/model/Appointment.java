package eus.fpsanturtzilh.pag.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

/**
 * Appointment entitatea
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="appointments")
public class Appointment implements Serializable{

	private static final long serialVersionUID = 1L;
	
    /**
     * Appointment-aren identifikatzaile bakarra
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    /**
     * Hitzordua egingo den eserlekuaren zenbakia
     */
	@Column
	private int seat;

    /**
     * Hitzordua egingo den data.
     */
	@Column
	private LocalDate date;

    /**
     * Hitzorduaren hasiera-ordua.
     */
	@Column
	private LocalTime start_time;

    /**
     * Hitzorduaren amaiera-ordua.
     */
	@Column
	private LocalTime end_time;

    /**
     * Hitzorduari buruzko oharrak.
     */
	@Column
	private String comment;

    /**
     * Appointment bakoitza ikasle bakar bati dagokio
     */
	@ManyToOne
	@JoinColumn(name="student_id", nullable = false)
	@JsonBackReference(value = "appoint-stud")
	private Student students;

    /**
     * Appointment bakoitza bezero bakar bati dagokio
     */
	@ManyToOne
	@JoinColumn(name="client_id", nullable = false)
	@JsonBackReference(value = "appoint-cli")
	private Client clients;

    /**
     * Hitzorduaren izen laburra
     */
	@Column
	private String name;

    /**
     * Erregistroaren sortze, eguneratze edo ezabatze informazioa
     */
	@Embedded
	private TimestampInfo timestamps = new TimestampInfo();

    /**
     * Appointment batek hainbat zerbitzu izan ditzake
     * Erlazio hau N:M erlazio baten inplementazioa da, AppointmentService entitatearen bidez
     */
	@OneToMany (mappedBy="appointments", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "appoint-app_serv")
	private List<AppointmentService> appoinments_services;
	
}
