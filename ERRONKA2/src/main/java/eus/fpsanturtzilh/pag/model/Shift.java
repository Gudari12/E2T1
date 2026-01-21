package eus.fpsanturtzilh.pag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="shifts")
public class Shift {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String type;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable = false)
	@JsonIgnore
	private Student students;

	@Embedded
	private TimestampInfo info;
}