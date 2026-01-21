package eus.fpsanturtzilh.pag.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="groupsFroga")
public class GroupFroga {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Embedded
	private TimestampInfo info;
	
	@OneToMany (mappedBy="groups", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Schedule> schedules;
	
	@OneToMany (mappedBy="groups", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Student> students;
	
}
