package com.itso.occupancy.occupancy.controller.job;

import com.itso.occupancy.occupancy.dto.model.job.JobCreateDto;
import com.itso.occupancy.occupancy.dto.model.job.JobDto;
import com.itso.occupancy.occupancy.dto.model.job.JobIdDto;
import com.itso.occupancy.occupancy.dto.model.job.JobUpdateDto;
import com.itso.occupancy.occupancy.service.job.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@AllArgsConstructor
@Slf4j
public class JobController {
    private final JobService jobService;

    @GetMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<JobDto>> getAllJob(){return jobService.getAllJob();} //Récupère tous les jobs en get

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<JobDto> getJob(@PathVariable Long id) {return jobService.getJob(id);} //Récupère un job en get

    @PostMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<JobDto>> postAllJob(){return jobService.postAllJob();} //Récupère tous les jobs en post

    @PostMapping("/id")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<JobDto> postJob(@RequestBody JobIdDto jobIdDto) {return jobService.postJob(jobIdDto);} //Récupère un job en post

    @PostMapping("/create")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<JobDto> createJob(@RequestBody JobCreateDto JobCreateDto){ //Crée un job
        return jobService.createJob(JobCreateDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<Object> deleteJob(@PathVariable Long id){ //Supprime un job
        return jobService.deleteJob(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<JobDto> updateJob( @PathVariable Long id, @RequestBody JobUpdateDto jobUpdateDto){ //Modifie un job
        return jobService.updateJob(id, jobUpdateDto);
    }
}
