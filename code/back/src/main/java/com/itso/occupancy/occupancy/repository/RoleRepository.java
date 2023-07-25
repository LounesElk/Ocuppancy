package com.itso.occupancy.occupancy.repository;
import com.itso.occupancy.occupancy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByIdAndSupprimerIsFalse(Long id);
    List<Role> SupprimerIsFalse();
}
