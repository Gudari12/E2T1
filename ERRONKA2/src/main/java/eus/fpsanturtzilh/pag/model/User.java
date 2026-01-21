package eus.fpsanturtzilh.pag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String username;
	
	@Column
	private String email;
	
	@Column
	private String rol;

	@Embedded
	private TimestampInfo info;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "client_id", unique = true)
	@JsonIgnore
	private Client clients;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "student_id", unique = true)
	@JsonIgnore
	private Student students;
		
}