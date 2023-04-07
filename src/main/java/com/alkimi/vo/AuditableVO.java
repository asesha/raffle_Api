package com.alkimi.vo;

import java.time.LocalDate;

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
public class AuditableVO {
	
	protected LocalDate createdOn;
	
	protected String createdBy;
	
	protected LocalDate updatedOn;
	
	protected String updatedBy;

}
