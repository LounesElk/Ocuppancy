package com.itso.occupancy.occupancy.dto.mapper;


import com.itso.occupancy.occupancy.dto.model.user.UserCreateInputDto;
import com.itso.occupancy.occupancy.dto.model.user.UserDto;
import com.itso.occupancy.occupancy.model.Job;
import com.itso.occupancy.occupancy.model.Role;
import com.itso.occupancy.occupancy.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername())
                .setId_role(user.getRole().getId());
    }

    public static User toUserModel(UserCreateInputDto userCreateInputDto, String encryptedPassword,Job job, Role role) {
        return new User()
                .setFirstName(userCreateInputDto.getFirstName())
                .setLastName(userCreateInputDto.getLastName())
                .setEmail(userCreateInputDto.getEmail())
                .setUsername(userCreateInputDto.getUsername())
                .setPassword(encryptedPassword)
                .setJob(job)
                .setRole(role);

    }

    public static User toUserModel(UserDto userDto) {
        return new User()
                .setId(userDto.getId())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail())
                .setUsername(userDto.getUsername());
    }
    }
