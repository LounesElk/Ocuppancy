package com.itso.occupancy.occupancy.service.job;

import com.itso.occupancy.occupancy.dto.model.job.*;
import com.itso.occupancy.occupancy.model.Job;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface JobService {
    public ResponseEntity<List<JobDto>> getAllJob();
    public ResponseEntity<JobDto> getJob(Long id);
    public ResponseEntity<List<JobDto>> postAllJob();
    public ResponseEntity<JobDto> postJob(JobIdDto jobIdDto);
    public ResponseEntity<JobDto> createJob(JobCreateDto JobCreateDto);
    ResponseEntity<Object> deleteJob(Long id);
    ResponseEntity<JobDto> updateJob(Long id, JobUpdateDto jobUpdateDto);

}
