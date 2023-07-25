package com.itso.occupancy.occupancy.dto.model.workTask;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class WorkTaskWithoutDto {

    private Long id;
    private Date work_day;
    private double work_time;
    private Long id_tag;
    private Long id_user;
    private String commentaire;
    private Boolean vide;
    private double duree;
}

