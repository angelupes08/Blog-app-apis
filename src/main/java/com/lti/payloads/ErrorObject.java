package com.lti.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorObject {
	
	private Integer statusCode;
	
	private String message;
	
	private Date TimeStamp;


}
