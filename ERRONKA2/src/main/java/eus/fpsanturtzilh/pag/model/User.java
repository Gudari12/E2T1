package eus.fpsanturtzilh.pag.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
	
	@OneToOne
	@JoinColumn (name = "client_id", unique = true)
	@JsonBackReference(value = "client-user")
	private Client clients;
	
	@OneToOne
	@JoinColumn (name = "student_id", unique = true)
	@JsonBackReference(value = "student-user")
	private Student students;
		
}