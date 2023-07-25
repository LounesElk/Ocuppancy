package com.itso.occupancy.occupancy.dto.model.workTask;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class WorkTaskUptadeDto {
    private Date work_day;
    private double work_time;
    private Long id_feature;
    private Long id_project;
    private Long id_tag;
    private Long id_user;
}
