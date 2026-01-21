package eus.fpsanturtzilh.pag.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TimestampInfo {

	@Column
	private LocalDateTime created_at;

	@Column
	private LocalDateTime updated_at;

	@Column
	private LocalDateTime deleted_at;
	
}
