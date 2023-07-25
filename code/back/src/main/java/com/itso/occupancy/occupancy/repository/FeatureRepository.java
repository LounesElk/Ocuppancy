package com.itso.occupancy.occupancy.repository;

import com.itso.occupancy.occupancy.model.Feature;
import com.itso.occupancy.occupancy.model.WorkTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findAllByProjectIdAndProjectSupprimerIsFalseAndSupprimerIsFalse(Long id);
    List<Feature> ProjectSupprimerIsFalseAndSupprimerIsFalseOrderByName();
    Optional<Feature> findByIdAndProjectSupprimerIsFalseAndSupprimerIsFalse(Long id);
    List<Feature> findByNameContainingIgnoreCaseAndSupprimerIsFalseOrderByName(String name);
    List<Feature> findByNameContainingIgnoreCaseAndProjectIdAndSupprimerIsFalseOrderByName(String name,Long id);

}
