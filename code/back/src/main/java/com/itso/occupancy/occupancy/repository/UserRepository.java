package com.itso.occupancy.occupancy.repository;

import com.itso.occupancy.occupancy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsernameAndIsDeletedIsFalseAndJobSupprimerIsNullOrJobSupprimerIsFalseAndIsDeletedIsFalseAndUsername(String username,String username2);

    List<User> findByJobIdAndIsDeletedIsFalseAndJobSupprimerIsFalse(Long id);
    List<User> IsDeletedIsFalseAndJobSupprimerIsNullOrJobSupprimerIsFalseAndIsDeletedIsFalseOrderByRoleId();
    List<User> JobSupprimerIsNullOrJobSupprimerIsFalse();
    List<User> findByRoleNameIsNotAndIsDeletedIsFalseAndJobSupprimerIsNullOrJobSupprimerIsFalseAndIsDeletedIsFalseAndRoleNameIsNot(String a, String b);
    Optional<User> findByIdAndIsDeletedIsFalseAndJobSupprimerIsFalseOrJobSupprimerIsNullAndIsDeletedIsFalseAndId(Long id,Long id2);
    List<User> findByFirstNameContainingIgnoreCaseAndIsDeletedIsFalseAndJobSupprimerIsFalseOrFirstNameContainingIgnoreCaseAndJobSupprimerIsNullAndIsDeletedIsFalseOrderByRoleId(String name, String Name2);
}
