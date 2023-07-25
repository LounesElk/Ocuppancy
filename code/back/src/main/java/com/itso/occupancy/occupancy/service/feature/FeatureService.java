package com.itso.occupancy.occupancy.service.feature;

import com.itso.occupancy.occupancy.dto.model.feature.*;
import com.itso.occupancy.occupancy.dto.model.project.ProjectUserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FeatureService {
    public ResponseEntity<List<FeatureDto>> getAllFeature();
    public ResponseEntity<FeatureDto> getFeature(Long id);
    public ResponseEntity<List<FeatureDto>> getAllFeatureByProject(Long id);
    public ResponseEntity<List<FeatureDto>> postAllFeature();
    public ResponseEntity<FeatureDto> postFeature(FeatureIdDto featureIdDto);
    public ResponseEntity<List<FeatureDto>> postAllFeatureByProject(FeatureIdProjectDto featureIdProjectDto);
    public ResponseEntity<FeatureDto> createFeature(FeatureCreateDto featureCreateDto);
    ResponseEntity<Object> deleteFeature(Long id);
    ResponseEntity<FeatureDto> updateFeature(Long id, FeatureUpdateDto featureUpdateDto);
    public ResponseEntity<List<FeatureDto>> rechercheByName(FeatureRechercheDto recherche);
    public ResponseEntity<List<FeatureDto>> rechercheByNameProject(FeatureRechercheDto recherche);
    public  ResponseEntity<List<FeatureUserDto>> getAllFeatureAndUser();
    public  ResponseEntity<List<FeatureUserDto>> getFeatureAndUser(Long id);

}
