package com.itso.occupancy.occupancy.dto.model.feature;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class FeatureDto {
    private  Long id;
    private int code;
    private String name;
    private Long id_project;
    private double Temps_E;
    private Date Date_E;
    private double Temps_R;
    private Date Date_R;

}
