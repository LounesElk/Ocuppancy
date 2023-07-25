package com.itso.occupancy.occupancy.dto.model.login;

import lombok.Data;

@Data
public class JwtAuthPublicDto {
	
	// Main Parameters
	private String 	jwttoken;
	private	String	type = "Bearer";
	
	/**
	 * Default constructor
	 */
	public JwtAuthPublicDto() {
	}
	
	/**
	 * Configured constructor
	 * @param jwttoken
	 */
	public JwtAuthPublicDto(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	
}
