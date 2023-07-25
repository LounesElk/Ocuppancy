package com.itso.occupancy.occupancy.service.feature;

import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.dto.mapper.FeatureMapper;
import com.itso.occupancy.occupancy.dto.mapper.ProjectMapper;
import com.itso.occupancy.occupancy.dto.mapper.UserMapper;
import com.itso.occupancy.occupancy.dto.mapper.WorkTaskMapper;
import com.itso.occupancy.occupancy.dto.model.feature.*;
import com.itso.occupancy.occupancy.dto.model.user.UserDto;
import com.itso.occupancy.occupancy.dto.model.workTask.WorkTaskDto;
import com.itso.occupancy.occupancy.model.Client;
import com.itso.occupancy.occupancy.model.Feature;
import com.itso.occupancy.occupancy.model.Project;
import com.itso.occupancy.occupancy.model.WorkTask;
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
public class FeatureServiceImpl implements FeatureService{
    private final FeatureRepository featureRepository;
    private final ProjectRepository projectRepository;
    private final WorkTaskRepository workTaskRepository;

    @Override
    public ResponseEntity<List<FeatureDto>> getAllFeature(){
        //Liste de toutes les feature
        List<Feature> AllFeature = featureRepository.ProjectSupprimerIsFalseAndSupprimerIsFalseOrderByName();
        List<FeatureDto> featureDto = new ArrayList<>();
        for (Feature feature : AllFeature) {
            double temps = 0;
            //Liste de toutes les task par feature
            List<WorkTask> allWorkTaskByBIdFeature = workTaskRepository.findByFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsFalseAndFeatureSupprimerIsFalseAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsNullAndFeatureSupprimerIsNullAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrderById(feature.getId(),feature.getId());
            for(WorkTask workTaskDto : allWorkTaskByBIdFeature){
                temps = temps + workTaskDto.getWorkTime();
            }
            Date date= feature.getDate_E();
            if (!allWorkTaskByBIdFeature.isEmpty()) {
                date = allWorkTaskByBIdFeature.get(0).getWorkDay();
            }
            featureDto.add(FeatureMapper.toFeatureDto(feature,date,temps));
        }
        return new ResponseEntity<>(featureDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FeatureDto> getFeature(Long id){
        FeatureDto Feature = FeatureMapper.toFeatureDto(findFeatureIfExists(id));
        return new ResponseEntity<>(Feature, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FeatureDto>> getAllFeatureByProject(Long id){
        ///List des features
        List<Feature> AllFeatureByProject = featureRepository.findAllByProjectIdAndProjectSupprimerIsFalseAndSupprimerIsFalse(id);

        //Initialisation des variables
        List<FeatureDto> featureDto = new ArrayList<>();

        for (Feature feature : AllFeatureByProject) {
            //Initialisation des variables
            double temps = 0;
            Date date= feature.getDate_E();

            //Requête pour avoir toutes les tasks par features
            List<WorkTask> allWorkTaskByBIdFeature = workTaskRepository.findByFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsFalseAndFeatureSupprimerIsFalseAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsNullAndFeatureSupprimerIsNullAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrderById(feature.getId(),feature.getId());

            //Calcule du temps estimé
            for(WorkTask workTaskDto : allWorkTaskByBIdFeature){
                temps = temps + workTaskDto.getWorkTime();
            }

            //On récupère la premier date si la liste de de task n'est pas vide
            if (!allWorkTaskByBIdFeature.isEmpty()) {
                date = allWorkTaskByBIdFeature.get(0).getWorkDay();
            }

            //Création de l'objet
            featureDto.add(FeatureMapper.toFeatureDto(feature,date,temps));
        }
        return new ResponseEntity<>(featureDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FeatureDto>> postAllFeature(){
        List<FeatureDto> AllFeature = featureRepository.ProjectSupprimerIsFalseAndSupprimerIsFalseOrderByName().stream().map(FeatureMapper::toFeatureDto).toList();
        return new ResponseEntity<>(AllFeature, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FeatureDto> postFeature(FeatureIdDto featureIdDto){
        FeatureDto Feature = FeatureMapper.toFeatureDto(findFeatureIfExists(featureIdDto.getId()));
        return new ResponseEntity<>(Feature, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FeatureDto>> postAllFeatureByProject(FeatureIdProjectDto featureIdProjectDto){
        List<FeatureDto> AllFeatureByProject = featureRepository.findAllByProjectIdAndProjectSupprimerIsFalseAndSupprimerIsFalse(featureIdProjectDto.getId_project()).stream().map(FeatureMapper::toFeatureDto).toList();
        return new ResponseEntity<>(AllFeatureByProject, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FeatureDto> createFeature(FeatureCreateDto featureCreateDto){
        Project project = findProjectIfExists(featureCreateDto.getId_project());
        Feature newFeature = FeatureMapper.toFeatureModel(featureCreateDto,project);
        Feature feature = featureRepository.save(newFeature);
        FeatureDto featureDto= FeatureMapper.toFeatureDto(feature);
        return new ResponseEntity<>(featureDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteFeature(Long id){
        Feature feature = findFeatureIfExists(id);
        feature.setSupprimer(Boolean.TRUE);
        featureRepository.save(feature);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<FeatureDto> updateFeature(Long id, FeatureUpdateDto featureUpdateDto){
        Project project = findProjectIfExists(featureUpdateDto.getId_project());

        Feature feature = findFeatureIfExists(id);
        feature.setCode(featureUpdateDto.getCode()).setName(featureUpdateDto.getName())
                .setTemps_E(featureUpdateDto.getTemps_E()).setDate_E(featureUpdateDto.getDate_E())
                .setProject(project).setSupprimer(Boolean.FALSE);
        Feature feature1 = featureRepository.save(feature);
        FeatureDto featureDto = FeatureMapper.toFeatureDto(feature1);
        return  new ResponseEntity<>(featureDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FeatureDto>> rechercheByName(FeatureRechercheDto recherche){
        //Liste de toutes les feature
        List<Feature> AllFeature = featureRepository.findByNameContainingIgnoreCaseAndSupprimerIsFalseOrderByName(recherche.getName());

        //Initialisation des variables
        List<FeatureDto> featureDto = new ArrayList<>();

        for (Feature feature : AllFeature) {
            //Initialisation des variables
            double temps = 0;
            Date date= feature.getDate_E();

            //Liste de toutes les task par feature
            List<WorkTask> allWorkTaskByBIdFeature = workTaskRepository.findByFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsFalseAndFeatureSupprimerIsFalseAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsNullAndFeatureSupprimerIsNullAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrderById(feature.getId(),feature.getId());

            //Calcul du temps
            for(WorkTask workTaskDto : allWorkTaskByBIdFeature){
                temps = temps + workTaskDto.getWorkTime();
            }

            //calcule de la premier date si il y a des taches pour la feature
            if (!allWorkTaskByBIdFeature.isEmpty()) {
                date = allWorkTaskByBIdFeature.get(0).getWorkDay();
            }

            //Création de l'objet
            featureDto.add(FeatureMapper.toFeatureDto(feature,date,temps));
        }
        return new ResponseEntity<>(featureDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FeatureDto>> rechercheByNameProject(FeatureRechercheDto recherche){
        //Liste de toutes les feature par project
        List<Feature> AllFeature = featureRepository.findByNameContainingIgnoreCaseAndProjectIdAndSupprimerIsFalseOrderByName(recherche.getName(), recherche.getId_project());

        //Initialisation des variables
        List<FeatureDto> featureDto = new ArrayList<>();

        for (Feature feature : AllFeature) {
            //Initialisation des variables
            double temps = 0;
            Date date= feature.getDate_E();

            //Liste de toutes les task par feature
            List<WorkTask> allWorkTaskByBIdFeature = workTaskRepository.findByFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsFalseAndFeatureSupprimerIsFalseAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsNullAndFeatureSupprimerIsNullAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrderById(feature.getId(),feature.getId());

            //Calcule du temps
            for(WorkTask workTaskDto : allWorkTaskByBIdFeature){
                temps = temps + workTaskDto.getWorkTime();
            }

            //calcule de la premier date si il y a des taches pour la feature
            if (!allWorkTaskByBIdFeature.isEmpty()) {
                date = allWorkTaskByBIdFeature.get(0).getWorkDay();
            }

            //Création de l'objet
            featureDto.add(FeatureMapper.toFeatureDto(feature,date,temps));
        }
        return new ResponseEntity<>(featureDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FeatureUserDto>> getAllFeatureAndUser(){
        //Liste de toutes les feature
        List<Feature> AllFeature = featureRepository.ProjectSupprimerIsFalseAndSupprimerIsFalseOrderByName();

        //Initialisation des variables
        List<FeatureUserDto> featureDto = new ArrayList<>();

        for (Feature feature : AllFeature) {
            //Initialisation des variables
            double temps = 0;
            Set<UserDto> userDto = new HashSet<>();
            Date date= feature.getDate_E();

            //Requête pour avoir toutes les tâches par feature
            List<WorkTask> listWorktask = workTaskRepository.findByFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsFalseAndFeatureSupprimerIsFalseAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsNullAndFeatureSupprimerIsNullAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrderById(feature.getId(), feature.getId());

            //Calcule des utilisateurs et du temps réel
            for (WorkTask workTask : listWorktask) {
                userDto.add(UserMapper.toUserDto(workTask.getUser()));
                temps = temps + workTask.getWorkTime();
            }

            //calcule de la premier date si il y a des taches pour la feature
            if (!listWorktask.isEmpty()) {
                date = listWorktask.get(0).getWorkDay();
            }

            //Trie et création de l'objet
            List<UserDto> user = new ArrayList<>(userDto);
            featureDto.add(FeatureMapper.toFeatureUserDto(feature,date,temps,user));
        }
        return new ResponseEntity<>(featureDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FeatureUserDto>> getFeatureAndUser(Long id){
        //Liste de toutes les feature pour un project
        List<Feature> AllFeatureByProject = featureRepository.findAllByProjectIdAndProjectSupprimerIsFalseAndSupprimerIsFalse(id);
        //Initialisation des variables
        List<FeatureUserDto> featureDto = new ArrayList<>();

        for (Feature feature : AllFeatureByProject) {
            //Initialisation des variables
            double temps = 0;
            Set<UserDto> userDto = new HashSet<>();
            Date date= feature.getDate_E();

            //Requête pour avoir toutes les tâches par feature
            List<WorkTask> listWorktask = workTaskRepository.findByFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsFalseAndFeatureSupprimerIsFalseAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrFeatureIdAndSupprimerIsFalseAndProjectSupprimerIsNullAndFeatureSupprimerIsNullAndTagSupprimerIsFalseAndUserIsDeletedIsFalseOrderById(feature.getId(), feature.getId());

            //Calcule des utilisateurs et du temps réel
            for (WorkTask workTask : listWorktask) {
                userDto.add(UserMapper.toUserDto(workTask.getUser()));
                temps = temps + workTask.getWorkTime();
            }

            //calcule de la premier date si il y a des taches pour la feature
            if (!listWorktask.isEmpty()) {
                date = listWorktask.get(0).getWorkDay();
            }

            //Trie et création de l'objet
            List<UserDto> user = new ArrayList<>(userDto);
            featureDto.add(FeatureMapper.toFeatureUserDto(feature,date,temps,user));
        }
        return new ResponseEntity<>(featureDto, HttpStatus.OK);
    }
    //Gestion des erreurs

    private Feature findFeatureIfExists(Long id) {

        return featureRepository.findByIdAndProjectSupprimerIsFalseAndSupprimerIsFalse(id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find Feature [id = %s]", id)
                ));
    }
    private Project findProjectIfExists(Long id) {

        return projectRepository.findByIdAndSupprimerIsFalseAndClientSupprimerIsFalse(id).orElseThrow(() -> new ElementNotFoundException(
                String.format("Unable to find Project [id = %s] or it is deleted ", id)
        ));
    }
}
