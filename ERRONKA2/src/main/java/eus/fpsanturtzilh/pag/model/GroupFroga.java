package eus.fpsanturtzilh.pag.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="groupsFroga")
public class GroupFroga implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;

	@Embedded
	private TimestampInfo timestamps = new TimestampInfo();
	
	@OneToMany (mappedBy="groups", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "group-sched")
	private List<Schedule> schedules;
	
	@OneToMany (mappedBy="groups", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value = "group-student")
	private List<Student> students;
	
}
