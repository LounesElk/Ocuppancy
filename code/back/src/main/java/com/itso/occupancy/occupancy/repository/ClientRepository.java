package com.itso.occupancy.occupancy.repository;

import com.itso.occupancy.occupancy.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByIdAndSupprimerIsFalse(Long id);
    List<Client> SupprimerIsFalseOrderByName();
    List<Client>findByNameContainingIgnoreCaseAndSupprimerIsFalseOrderByName(String name);
}
