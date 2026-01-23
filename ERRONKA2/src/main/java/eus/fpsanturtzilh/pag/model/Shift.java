package eus.fpsanturtzilh.pag.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="shifts")
public class Shift implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String type;
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable = false)
	@JsonBackReference(value = "shift-student")
	private Student students;

	@Embedded
	private TimestampInfo info;
}