package com.alkimi.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Auditable {
	
	@Column(name="CREATED_ON")
	protected LocalDate createdOn;
	
	@Column(name="CREATED_BY")
	protected String createdBy;
	
	@Column(name="UPDATED_ON")
	protected LocalDate updatedOn;
	
	@Column(name="UPDATED_BY")
	protected String updatedBy;

}
