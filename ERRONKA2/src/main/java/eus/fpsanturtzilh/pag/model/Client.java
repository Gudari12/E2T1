package eus.fpsanturtzilh.pag.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="clients")
public class Client {

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
	private TimestampInfo info;
	
	@OneToOne(mappedBy = "clients", cascade = CascadeType.ALL)
	@JsonIgnore
	private User users;

	@OneToMany (mappedBy="clients", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Appointment> appoinments;
}