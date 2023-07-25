package com.itso.occupancy.occupancy.dto.mapper;

import com.itso.occupancy.occupancy.dto.model.job.*;
import com.itso.occupancy.occupancy.model.Job;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JobMapper {
    public static JobDto toJobDto(Job job){
        return new JobDto()
                .setId(job.getId())
                .setCode(job.getCode())
                .setName(job.getName());
    }
    public static JobCreateDto toJobCreateDto(Job job){
        return new JobCreateDto().setCode(job.getCode())
                .setName(job.getName());
    }
    public static JobUpdateDto toJobUpdateDto(Job job){
        return new JobUpdateDto().setCode(job.getCode())
                .setName(job.getName());
    }
    public static Job toJobModel(JobCreateDto jobCreateDto){
        return new Job().setCode(jobCreateDto.getCode()).setName(jobCreateDto.getName());
    }
}
