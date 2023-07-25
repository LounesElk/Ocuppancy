package com.itso.occupancy.occupancy.dto.model.user;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserCreateInputDto {

    private String                  firstName;
    private String                  lastName;
    private String                  email;
    private String 	                username;
    private String                  password;
    private Long id_job;
    private Long id_role;
}
