package com.itso.occupancy.occupancy.service.user;


import com.itso.occupancy.occupancy.dto.model.project.ProjectDto;
import com.itso.occupancy.occupancy.dto.model.user.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

     ResponseEntity<UserDto> createNewUser(UserCreateInputDto userCreateInputDto);
     ResponseEntity<Object> deleteUser(Long id);
     ResponseEntity<UserDto> getSingleUser(Long id);
     ResponseEntity<List<UserDto>> getAllUsers();
     ResponseEntity<List<UserDto>> getAllUsersVerif();
     ResponseEntity<UserDto> updateUser(Long id , UserUpdateInputDto userUpdateInputDto);
     ResponseEntity<List<UserDto>> getAllUserByJob(Long id);
     ResponseEntity<List<UserDto>> getAllUserIsNotAdmin();
     ResponseEntity<List<UserDto>> rechercheByName(UserRechercheDto recherche);
     Boolean VerifMDP(UserVerifMdpDto userVerifMdpDto);

}
