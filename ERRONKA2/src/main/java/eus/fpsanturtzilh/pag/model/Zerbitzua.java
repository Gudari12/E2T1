package eus.fpsanturtzilh.pag.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="services")
public class Zerbitzua {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;

	@Column(precision = 10, scale = 2)
	private BigDecimal price;

	@Column(precision = 10, scale = 2)
	private BigDecimal home_price;
	
	@Column
	private int duration;
	
	@Embedded
	private TimestampInfo info;
	
	@OneToMany (mappedBy="services", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<AppointmentService> appoinments_services;
	
}