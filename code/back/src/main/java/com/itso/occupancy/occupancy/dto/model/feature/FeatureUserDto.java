package com.itso.occupancy.occupancy.dto.model.feature;

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
public class FeatureUserDto {

    private  Long id;
    private int code;
    private String name;
    private Long id_project;
    private double Temps_E;
    private Date Date_E;
    private double Temps_R;
    private Date Date_R;
    private List<UserDto> user;

}
