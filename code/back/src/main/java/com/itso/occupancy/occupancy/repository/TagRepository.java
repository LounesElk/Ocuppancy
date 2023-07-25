package com.itso.occupancy.occupancy.repository;

import com.itso.occupancy.occupancy.model.Project;
import com.itso.occupancy.occupancy.model.Tag;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByIdAndSupprimerIsFalse(Long id);
    List<Tag> SupprimerIsFalseOrderByName();
    List<Tag> findByNameContainingIgnoreCaseAndSupprimerIsFalseOrderByName(String name);
}
