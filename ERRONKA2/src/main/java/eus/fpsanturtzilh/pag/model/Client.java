package eus.fpsanturtzilh.pag.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

/**
 * Client entitatea
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="clients")
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	
    /**
     * Client-aren identifikatzaile bakarra
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private String phone;
	
	@Column
	private String email;

	@Column
	private Boolean home_client;

	@Embedded
	private TimestampInfo timestamps = new TimestampInfo();
	
	@OneToOne(mappedBy = "clients", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "client-user")
	private User users;

	@OneToMany (mappedBy="clients", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "appoint-cli")
	private List<Appointment> appoinments;
}