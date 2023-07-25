package com.itso.occupancy.occupancy.dto.model.workTask;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class WorkTaskDto {
    private Long id;
    private Date work_day;
    private double work_time;
    private Long id_feature;
    private Long id_project;
    private Long id_tag;
    private Long id_user;
    private String commentaire;
    private double duree;
}
