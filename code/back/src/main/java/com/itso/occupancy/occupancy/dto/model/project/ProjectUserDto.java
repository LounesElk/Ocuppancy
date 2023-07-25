package com.itso.occupancy.occupancy.dto.model.project;

import com.itso.occupancy.occupancy.dto.model.user.UserDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class ProjectUserDto {
    private Long id;
    private String name;
    private  Long id_client;
    private  List<UserDto> user;
    private double Temps_E;
    private double Temps_R;

}
