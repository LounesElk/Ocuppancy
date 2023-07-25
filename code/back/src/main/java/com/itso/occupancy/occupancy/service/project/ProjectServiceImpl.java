package com.itso.occupancy.occupancy.service.project;


import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.dto.mapper.*;
import com.itso.occupancy.occupancy.dto.model.project.*;
import com.itso.occupancy.occupancy.dto.model.user.*;
import com.itso.occupancy.occupancy.model.*;
import com.itso.occupancy.occupancy.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;
    private final WorkTaskRepository workTaskRepository;
    private final FeatureRepository featureRepository;

    @Override
    public  ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> allProjectsDto = projectRepository.SupprimerIsFalseAndClientSupprimerIsFalseOrderByName()
                .stream()
                .map(ProjectMapper::toProjectDto)
                .toList();
        return  new ResponseEntity<>(allProjectsDto, HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<List<ProjectDto>> postAllProjects() {
        List<ProjectDto> allProjectsDto = projectRepository.SupprimerIsFalseAndClientSupprimerIsFalseOrderByName()
                .stream()
                .map(ProjectMapper::toProjectDto)
                .toList();
        return  new ResponseEntity<>(allProjectsDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectDto> getProject(Long id){
        ProjectDto projectDto = ProjectMapper.toProjectDto(findProjectIfExists(id));
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectDto> postProject(ProjectIdDto projectIdDto){
        ProjectDto projectDto = ProjectMapper.toProjectDto(findProjectIfExists(projectIdDto.getId()));
         return new ResponseEntity<>(projectDto , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProjectDto>> getAllProjectByClient(Long id){
        List<ProjectDto> AllProjectByClient = projectRepository.findByClientIdAndSupprimerIsFalseAndClientSupprimerIsFalse(id).stream().map(ProjectMapper::toProjectDto).toList();
        return new ResponseEntity<>(AllProjectByClient, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProjectDto>> postAllProjectByClient(ProjectClientDto projectClientDto){
        List<ProjectDto> AllProjectByClient = projectRepository.findByClientIdAndSupprimerIsFalseAndClientSupprimerIsFalse(projectClientDto.getId_client()).stream().map(ProjectMapper::toProjectDto).toList();
        return new ResponseEntity<>(AllProjectByClient, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectDto> createProject(ProjectCreateDto projectCreateDto){
        Client client = findClientIfExists(projectCreateDto.getId_client());
        Project newProject = ProjectMapper.toProjectModel(projectCreateDto,client);
        Project project = projectRepository.save(newProject);
        ProjectDto projectDto = ProjectMapper.toProjectDto(project);
        return new ResponseEntity<>(projectDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> deleteProject(Long id){
        Project project = findProjectIfExists(id);
        project.setSupprimer(Boolean.TRUE);
        projectRepository.save(project);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ProjectDto> updateProject(Long id, ProjectUpdateDto projectUpdateDto){
        Client client = findClientIfExists(projectUpdateDto.getId_client());

        Project project = findProjectIfExists(id);
        project
                .setName(projectUpdateDto.getName())
                .setClient(client);
        Project Project = projectRepository.save(project);
        ProjectDto projectDto = ProjectMapper.toProjectDto(Project);
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<Object> deleteAllProject(List<ProjectIdDto>  projectIdDto){
        for (ProjectIdDto dto : projectIdDto) {
            Project project = findProjectIfExists(dto.getId());
            project.setSupprimer(Boolean.TRUE);
            projectRepository.save(project);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public  ResponseEntity<List<ProjectDto>> rechercheByName(ProjectRechercheDto recherche) {
        List<ProjectDto> allProjectsDto = projectRepository.findByNameContainingIgnoreCaseAndSupprimerIsFalseAndClientSupprimerIsFalseOrderByName(recherche.getName())
                .stream()
                .map(ProjectMapper::toProjectDto)
                .toList();
        return  new ResponseEntity<>(allProjectsDto, HttpStatus.OK);
    }

    @Override
    public  ResponseEntity<List<ProjectUserDto>> getAllProjectsAndUser() {
        //List des projects
        List<Project> allProjectsDto = projectRepository.SupprimerIsFalseAndClientSupprimerIsFalseOrderByName();

        //Initialisation des variables
        List<ProjectUserDto> Project = new ArrayList<>();
        Set<UserDto> userDto = new HashSet<>();

        for (Project project : allProjectsDto) {
            //Initialisation des variables
            double temps_R = 0;
            double temps_E = 0;

            //Requête pour avoir toutes les task et features par project
            List<WorkTask> listWorktask = workTaskRepository.findAllByProjectIdAndProjectSupprimerIsFalseAndFeatureSupprimerIsFalseAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrProjectIdAndSupprimerIsFalseAndProjectSupprimerIsNullAndFeatureSupprimerIsNullAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrderById(project.getId(), project.getId());
            List<Feature> AllFeatureByProject = featureRepository.findAllByProjectIdAndProjectSupprimerIsFalseAndSupprimerIsFalse(project.getId());

            //Calcule des utilisateurs et du temps réel
            for (WorkTask workTask : listWorktask) {
                userDto.add(UserMapper.toUserDto(workTask.getUser()));
                temps_R = temps_R + workTask.getWorkTime();
            }

            //Calcule  du temps estimé
            for (Feature feature : AllFeatureByProject) {
                temps_E = temps_E + feature.getTemps_E();
            }

            //Trie et création de l'objet
            List<UserDto> user = new ArrayList<>(userDto);
            Project.add(ProjectMapper.toProjectUserDto(project, user,temps_R,temps_E));
        }
        return  new ResponseEntity<>(Project, HttpStatus.OK);
    }

    @Override
    public  ResponseEntity <ProjectUserDto> getProjectsAndUser(Long id) {

        //Un project, requête pour trouver à quelle id correspond mon id
        Project project = findProjectIfExists(id);

        //Initialisation des variables
        Set<UserDto> userDto = new HashSet<>();
        double temps_R = 0;
        double temps_E = 0;

        //Requête pour avoir toutes les task et features par project
        List<WorkTask> listWorktask = workTaskRepository.findAllByProjectIdAndProjectSupprimerIsFalseAndFeatureSupprimerIsFalseAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrProjectIdAndSupprimerIsFalseAndProjectSupprimerIsNullAndFeatureSupprimerIsNullAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrderById(project.getId(), project.getId());
        List<Feature> AllFeatureByProject = featureRepository.findAllByProjectIdAndProjectSupprimerIsFalseAndSupprimerIsFalse(project.getId());

        //Calcule des utilisateurs et du temps réel
        for (WorkTask workTask : listWorktask) {
            userDto.add(UserMapper.toUserDto(workTask.getUser()));
            temps_R = temps_R + workTask.getWorkTime();
        }

        //Calcule  du temps estimé
        for (Feature feature : AllFeatureByProject) {
            temps_E = temps_E + feature.getTemps_E();
        }

        //Trie et création de l'objet
        List<UserDto> user = new ArrayList<>(userDto);

        //Utilisation du mapper pour obtenir mon object ProjectUserDto
        ProjectUserDto Project  = ProjectMapper.toProjectUserDto(project, user,temps_R,temps_E);

        return  new ResponseEntity<>(Project, HttpStatus.OK);
    }

    //Gestion des erreurs

    private Project findProjectIfExists(Long id) {

        return projectRepository.findByIdAndSupprimerIsFalseAndClientSupprimerIsFalse(id).orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find Project [id = %s] or it is deleted ", id)
                ));
    }
    private Client findClientIfExists(Long id) {

        return clientRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find Client [id = %s]", id)
                ));
    }



}
