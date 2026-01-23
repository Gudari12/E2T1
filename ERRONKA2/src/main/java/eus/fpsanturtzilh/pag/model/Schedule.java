package eus.fpsanturtzilh.pag.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="schedules")
public class Schedule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="group_id", nullable = false)
	@JsonBackReference(value = "group-sched")
	private GroupFroga groups;
	
	@Column
	private int day;
	
	@Column
	private LocalDate start_date;
	
	@Column
	private LocalDate end_date;

	@Column
	private LocalTime start_time;
	
	@Column
	private LocalTime end_time;
	
	@Embedded
	private TimestampInfo info;
}
