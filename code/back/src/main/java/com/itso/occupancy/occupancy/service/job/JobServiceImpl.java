package com.itso.occupancy.occupancy.service.job;

import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.dto.mapper.JobMapper;
import com.itso.occupancy.occupancy.dto.model.job.*;
import com.itso.occupancy.occupancy.model.Job;
import com.itso.occupancy.occupancy.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class JobServiceImpl implements JobService{
    private final JobRepository jobRepository;

    @Override
    public ResponseEntity<List<JobDto>> getAllJob(){
        List<JobDto> allJob = jobRepository.SupprimerIsFalse().stream().map(JobMapper::toJobDto).toList();
        return new ResponseEntity<>(allJob, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<JobDto> getJob(Long id){
        JobDto Job = JobMapper.toJobDto(findJobIfExists(id));
        return new ResponseEntity<>(Job, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<JobDto>> postAllJob(){
        List<JobDto> allJob = jobRepository.SupprimerIsFalse().stream().map(JobMapper::toJobDto).toList();
        return new ResponseEntity<>(allJob, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<JobDto> postJob(JobIdDto jobIdDto){
        JobDto Job = JobMapper.toJobDto(findJobIfExists(jobIdDto.getId()));
        return new ResponseEntity<>(Job, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<JobDto> createJob(JobCreateDto jobCreateDto){
        Job job = JobMapper.toJobModel(jobCreateDto);
        Job job1 = jobRepository.save(job);
        JobDto jobDto = JobMapper.toJobDto(job1);
        return new ResponseEntity<>(jobDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteJob(Long id){
        Job job =findJobIfExists(id);
        job.setSupprimer(Boolean.TRUE);
        jobRepository.save(job);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<JobDto> updateJob(Long id, JobUpdateDto jobUpdateDto){
        Job job = findJobIfExists(id);
        job.setCode(jobUpdateDto.getCode()).setName(jobUpdateDto.getName());
        Job job1 = jobRepository.save(job);
        JobDto jobDto = JobMapper.toJobDto(job1);
        return new ResponseEntity<>(jobDto, HttpStatus.OK);
    }
    //fontion
    private Job findJobIfExists(Long id) {

        return jobRepository.findByIdAndSupprimerIsFalse(id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find Job [id = %s]", id)
                ));
    }

}
