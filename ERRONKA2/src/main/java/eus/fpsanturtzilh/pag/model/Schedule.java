package eus.fpsanturtzilh.pag.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="schedules")
public class Schedule {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="group_id", nullable = false)
	@JsonIgnore
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
