package com.itso.occupancy.occupancy.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ErrorPublicDto {

	private int 			errorCode;
	private LocalDateTime 	errorDate;
	private String 			message;

	public ErrorPublicDto(int errorCode,
                          LocalDateTime errorDate,
                          String message) {
		this.errorCode 	= errorCode;
		this.errorDate 	= errorDate;
		this.message 	= message;
	}
}
