package com.itso.occupancy.occupancy.dto.model.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JwtAuthInputDto {

    // Main Parameters
    @NotBlank
    private 				String 	username;

    @NotBlank
    private 				String 	password;
}
