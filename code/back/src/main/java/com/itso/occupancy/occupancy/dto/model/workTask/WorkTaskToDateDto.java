package com.itso.occupancy.occupancy.dto.model.workTask;

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
public class WorkTaskToDateDto {
    private Date work_day;
    private Long id_user;
}
