package eus.fpsanturtzilh.pag.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="services")
public class Zerbitzua implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
	@JsonManagedReference(value = "app_serv-zerb")
	private List<AppointmentService> appoinments_services;
	
}