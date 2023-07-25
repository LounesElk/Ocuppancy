package com.itso.occupancy.occupancy.repository;

import com.itso.occupancy.occupancy.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByIdAndSupprimerIsFalse(Long id);
    List<Job> SupprimerIsFalse();
}
