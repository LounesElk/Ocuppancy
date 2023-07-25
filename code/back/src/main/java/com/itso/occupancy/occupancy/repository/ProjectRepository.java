package com.itso.occupancy.occupancy.repository;

import com.itso.occupancy.occupancy.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByClientIdAndSupprimerIsFalseAndClientSupprimerIsFalse(Long id);
    List<Project>SupprimerIsFalseAndClientSupprimerIsFalseOrderByName();
    Optional<Project> findByIdAndSupprimerIsFalseAndClientSupprimerIsFalse(Long id);
    List<Project>findByNameContainingIgnoreCaseAndSupprimerIsFalseAndClientSupprimerIsFalseOrderByName(String name);

}
