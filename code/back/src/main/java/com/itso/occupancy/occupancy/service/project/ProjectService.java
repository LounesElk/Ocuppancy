package com.itso.occupancy.occupancy.service.project;


import com.itso.occupancy.occupancy.dto.model.project.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {
    public  ResponseEntity<List<ProjectDto>> getAllProjects();
    public  ResponseEntity<List<ProjectDto>> postAllProjects();
    public  ResponseEntity<ProjectDto> getProject(Long id);
    public  ResponseEntity<ProjectDto> postProject(ProjectIdDto projectIdDto);
    public  ResponseEntity<List<ProjectDto>> getAllProjectByClient(Long id);
    public  ResponseEntity<List<ProjectDto>> postAllProjectByClient(ProjectClientDto projectClientDto);
    public ResponseEntity<ProjectDto> createProject(ProjectCreateDto projectCreateDto);
    ResponseEntity<Object> deleteProject(Long id);
    ResponseEntity<ProjectDto> updateProject(Long id, ProjectUpdateDto projectUpdateDto);
    ResponseEntity<Object> deleteAllProject(List<ProjectIdDto>  projectIdDto);
    public  ResponseEntity<List<ProjectDto>> rechercheByName(ProjectRechercheDto recherche);
    public  ResponseEntity<List<ProjectUserDto>> getAllProjectsAndUser();
    public  ResponseEntity <ProjectUserDto> getProjectsAndUser(Long id);

}
