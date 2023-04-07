package com.alkimi.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private String code = null;

	private String reason = null;

	private String status = null;
	
	private List<String> errorMessages = new ArrayList<>();

}
