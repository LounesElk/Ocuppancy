package com.itso.occupancy.occupancy.controller.user;


import com.itso.occupancy.occupancy.dto.model.user.*;
import com.itso.occupancy.occupancy.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    /**
     * Url to get all users
     * @return ResponseEntity<List < UserDto>>
     */
    @GetMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<UserDto>> getAllUsersResponse() { //Récupère des utilisateurs en get
        return userService.getAllUsers();
    }

    @GetMapping("/verif")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<UserDto>> getAllUsersVerif() { //Récupere la liste de tous les users mêmes ceux supprimer
        return userService.getAllUsersVerif();
    }


    /**
     * Url to get a single users
     * @param id the id of the user to get
     * @return ResponseEntity<UserDto>
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<UserDto> getSingleUserResponse(@PathVariable Long id) { //Récupère un utilisateur en get
        return userService.getSingleUser(id);
    }

    /**
     * Url to update user
     * @param id                 the id of the user to update
     * @param userUpdateInputDto the input data to update with
     * @return ResponseEntity<UserDto>
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<UserDto> updateUserResponse(@PathVariable Long id, @RequestBody UserUpdateInputDto userUpdateInputDto) { //Récupère un utilisateur en post
        return userService.updateUser(id, userUpdateInputDto);
    }

    /**
     * Url to delete user
     * @param id the user to delete
     * @return HttpStatus.NO_CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserResponse(@PathVariable Long id) { //Supprime un user
        return userService.deleteUser(id);
    }

    /**
     * Url to create new user
     * @param userCreateInputDto the input data to create with
     * @return ResponseEntity<UserDto>
     */
    @PostMapping("/create")
    public ResponseEntity<UserDto> createNewUserResponse(@RequestBody UserCreateInputDto userCreateInputDto) { //Crée un user
        return userService.createNewUser(userCreateInputDto);
    }

    @GetMapping("/job/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<UserDto>> getAllUserByJob(@PathVariable Long id){ // Récupère tous les utilisateurs d'un job
        return userService.getAllUserByJob(id);
    }

    @GetMapping("/users")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<UserDto>> getAllUserIsNotAdmin(){ //Récupère tous les utilisateurs qui ne sont pas admin
        return userService.getAllUserIsNotAdmin();
    }

    @PostMapping("/recherche")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<UserDto>> rechercheByName(@RequestBody UserRechercheDto recherche){ // Recherche
        return userService.rechercheByName(recherche);
    }

    @PostMapping("/verifmdp")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public Boolean VerifMDP(@RequestBody UserVerifMdpDto userVerifMdpDto){ // Verif mdp
        return userService.VerifMDP(userVerifMdpDto);
    }
}
