package com.itso.occupancy.occupancy.dto.model.feature;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class FeatureCreateDto {
    private int code;
    private String name;
    private Long id_project;
    private double Temps_E;
    private Date Date_E;
}
