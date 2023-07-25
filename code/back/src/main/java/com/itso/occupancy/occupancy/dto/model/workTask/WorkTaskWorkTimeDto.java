package com.itso.occupancy.occupancy.dto.model.workTask;

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
public class WorkTaskWorkTimeDto {
    private double work_time;
}
