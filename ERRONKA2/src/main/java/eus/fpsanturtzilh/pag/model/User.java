package eus.fpsanturtzilh.pag.model;

import java.io.Serializable;

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
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "client_id", unique = true)
	private Client clients;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "student_id", unique = true)
	private Student students;
		
}