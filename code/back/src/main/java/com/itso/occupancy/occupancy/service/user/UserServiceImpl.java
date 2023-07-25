package com.itso.occupancy.occupancy.service.user;

import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.dto.mapper.UserMapper;
import com.itso.occupancy.occupancy.dto.model.feature.FeatureDto;
import com.itso.occupancy.occupancy.dto.model.user.*;
import com.itso.occupancy.occupancy.model.Job;
import com.itso.occupancy.occupancy.model.Role;
import com.itso.occupancy.occupancy.model.User;
import com.itso.occupancy.occupancy.model.WorkTask;
import com.itso.occupancy.occupancy.repository.JobRepository;
import com.itso.occupancy.occupancy.repository.RoleRepository;
import com.itso.occupancy.occupancy.repository.UserRepository;
import com.itso.occupancy.occupancy.repository.WorkTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JobRepository jobRepository;

    @Override
    public ResponseEntity<UserDto> createNewUser(UserCreateInputDto userCreateInputDto) {
        Job job = findJobIfExists(userCreateInputDto.getId_job());
        Role role = findRoleIfExists(userCreateInputDto.getId_role());
        String encryptedPassword = passwordEncoder.encode(userCreateInputDto.getPassword());

        User newUser = UserMapper.toUserModel(userCreateInputDto, encryptedPassword,job, role );

        userRepository.save(newUser);

        UserDto newUserDto = UserMapper.toUserDto(newUser);

        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        User existingUser = findUserIfExists(id);
        existingUser.setDeleted(Boolean.TRUE);
        userRepository.save(existingUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

    @Override
    public ResponseEntity<UserDto> getSingleUser(Long id) {
        UserDto userDto = UserMapper.toUserDto(findUserIfExists(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsersDto = userRepository.IsDeletedIsFalseAndJobSupprimerIsNullOrJobSupprimerIsFalseAndIsDeletedIsFalseOrderByRoleId()
                .stream()
                .map(UserMapper::toUserDto)
                .toList();

        return new ResponseEntity<>(allUsersDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsersVerif() {
        List<UserDto> allUsersDto = userRepository.JobSupprimerIsNullOrJobSupprimerIsFalse()
                .stream()
                .map(UserMapper::toUserDto)
                .toList();

        return new ResponseEntity<>(allUsersDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long id, UserUpdateInputDto userUpdateInputDto) {
        User existingUser = findUserIfExists(id);
        existingUser
                .setFirstName(userUpdateInputDto.getFirstName())
                .setLastName(userUpdateInputDto.getLastName())
                .setEmail(userUpdateInputDto.getEmail())
                .setUsername(userUpdateInputDto.getUsername());

        if (userUpdateInputDto.getNewPassword() != null ){
            if (!passwordEncoder.matches(userUpdateInputDto.getNewPassword(), existingUser.getPassword())) {

                String encryptedNewPassword = passwordEncoder.encode(userUpdateInputDto.getNewPassword());
                existingUser.setPassword(encryptedNewPassword);
            }}
        //Todo handel bad password exception (Nabil 15/11/2022)

        User userSaved = userRepository.save(existingUser);

        return new ResponseEntity<>(UserMapper.toUserDto(userSaved), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUserByJob(Long id){
        List<UserDto> AllUserByJob = userRepository.findByJobIdAndIsDeletedIsFalseAndJobSupprimerIsFalse(id).stream().map(UserMapper::toUserDto).toList();
        return  new ResponseEntity<>(AllUserByJob, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUserIsNotAdmin(){
        String a = "ADMIN";
        List<UserDto> AllUserIsNotAdmin = userRepository.findByRoleNameIsNotAndIsDeletedIsFalseAndJobSupprimerIsNullOrJobSupprimerIsFalseAndIsDeletedIsFalseAndRoleNameIsNot(a, a).stream().map(UserMapper::toUserDto).toList();
        return  new ResponseEntity<>(AllUserIsNotAdmin, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> rechercheByName(UserRechercheDto recherche){
        List<UserDto> AllUserByJob = userRepository.findByFirstNameContainingIgnoreCaseAndIsDeletedIsFalseAndJobSupprimerIsFalseOrFirstNameContainingIgnoreCaseAndJobSupprimerIsNullAndIsDeletedIsFalseOrderByRoleId(recherche.getFirstName(),recherche.getFirstName()).stream().map(UserMapper::toUserDto).toList();
        return  new ResponseEntity<>(AllUserByJob, HttpStatus.OK);
    }

    @Override
    public Boolean VerifMDP(UserVerifMdpDto userVerifMdpDto){
        Boolean Test = false;
        User user = findUserIfExists(userVerifMdpDto.getId());
        String Mdp = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(userVerifMdpDto.getPassword());
        if (passwordEncoder.matches(userVerifMdpDto.getPassword(), user.getPassword())){Test = true;}
        return  Test;
    }


    /*
     * Private Methods
     */

    private User findUserIfExists(Long id) {

        return userRepository.findByIdAndIsDeletedIsFalseAndJobSupprimerIsFalseOrJobSupprimerIsNullAndIsDeletedIsFalseAndId(id, id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find User [id = %s]", id)
                ));
    }
    private Role findRoleIfExists(Long id) {

        return roleRepository.findByIdAndSupprimerIsFalse(id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find User [id = %s]", id)
                ));
    }
    private Job findJobIfExists(Long id) {

        return jobRepository.findByIdAndSupprimerIsFalse(id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find Job [id = %s]", id)
                ));
    }


}
