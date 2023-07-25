package com.itso.occupancy.occupancy.dto.model.user;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class UserDto {
    private Long                    id;
    private Long                    id_role;
    private String                  firstName;
    private String                  lastName;
    private String                  email;
    private String 	                username;
}
