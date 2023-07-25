package com.itso.occupancy.occupancy.controller.feature;

import com.itso.occupancy.occupancy.dto.model.feature.*;
import com.itso.occupancy.occupancy.service.feature.FeatureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature")
@AllArgsConstructor
@Slf4j
public class FeatureController {
    private final FeatureService featureService;

    @GetMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<FeatureDto>> getAllFeature(){ //Récupère Toutes les Feature par  get
        return featureService.getAllFeature();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<FeatureDto> getFeature(@PathVariable Long id) { //Récupère une Feature par id en méthode get
        return featureService.getFeature(id);
    }

    @GetMapping("/project/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<FeatureDto>> getAllFeatureByProject(@PathVariable Long id){ //Récupère toutes les features pour un project en get
        return featureService.getAllFeatureByProject(id);
    }

    @PostMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<FeatureDto>> postAllFeature(){ //Récupère Toutes les Feature par post
        return featureService.postAllFeature();
    }

    @PostMapping("/id")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<FeatureDto> postFeature(@RequestBody FeatureIdDto featureIdDto) { //Récupère une Feature en  get
        return featureService.postFeature(featureIdDto);
    }

    @PostMapping("/project")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<FeatureDto>> postAllFeatureByProject(@RequestBody FeatureIdProjectDto featureIdProjectDto){ //Récupère toutes les features pour un project en post
        return featureService.postAllFeatureByProject(featureIdProjectDto);
    }

    @PostMapping("/create")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<FeatureDto> createFeature(@RequestBody FeatureCreateDto featureCreateDto){ //Crée une features
        return featureService.createFeature(featureCreateDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<Object> deleteFeature(@PathVariable Long id){ //Supprime une feature
        return featureService.deleteFeature(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<FeatureDto> updateFeature(@PathVariable Long id, @RequestBody FeatureUpdateDto featureUpdateDto){ //Modifie une feature
        return featureService.updateFeature(id, featureUpdateDto);
    }

    @PostMapping("/recherche")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<FeatureDto>> rechercheByName(@RequestBody FeatureRechercheDto recherche){ //Recherche une feaeture
        return featureService.rechercheByName(recherche);
    }

    @PostMapping("/rechercheProject")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<FeatureDto>> rechercheByNameProject(@RequestBody FeatureRechercheDto recherche){ //Recherche une feature pour un project
        return featureService.rechercheByNameProject(recherche);
    }
    @GetMapping("/user")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<FeatureUserDto>> getAllFeatureAndUser() { //Récupère toutes les features avec les utilisateurs concernés et la temps pour le project
        return featureService.getAllFeatureAndUser();
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<FeatureUserDto>> getFeatureAndUser(@PathVariable Long id) { //Récupère toutes les features avec les utilisateurs concernés et la temps pour le project
        return featureService.getFeatureAndUser(id);
    }
}

