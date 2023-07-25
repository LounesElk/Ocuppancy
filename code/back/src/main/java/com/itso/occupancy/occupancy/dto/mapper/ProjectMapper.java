package com.itso.occupancy.occupancy.dto.mapper;

import com.itso.occupancy.occupancy.dto.model.project.ProjectCreateDto;
import com.itso.occupancy.occupancy.dto.model.project.ProjectDto;
import com.itso.occupancy.occupancy.dto.model.project.ProjectUpdateDto;
import com.itso.occupancy.occupancy.dto.model.project.ProjectUserDto;
import com.itso.occupancy.occupancy.dto.model.user.UserDto;
import com.itso.occupancy.occupancy.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProjectMapper {
    public static ProjectDto toProjectDto(Project project){
        return new ProjectDto()
                .setId(project.getId())
                .setName(project.getName())
                .setId_client(project.getClient().getId());
    }

    public static ProjectUserDto toProjectUserDto(Project project, List<UserDto> user, double temps_R,double temps_E ){
        return new ProjectUserDto()
                .setId(project.getId())
                .setName(project.getName())
                .setId_client(project.getClient().getId())
                .setUser(user)
                .setTemps_R(temps_R)
                .setTemps_E(temps_E);
    }

    public static ProjectCreateDto toProjectCreateDto(Project project){
        return new ProjectCreateDto()
                .setName(project.getName())
                .setId_client(project.getClient().getId());
    }
    public static ProjectUpdateDto toProjectUpdateDto(Project project){
        return new ProjectUpdateDto()
                .setName(project.getName())
                .setId_client(project.getClient().getId());
    }
    public static Project toProjectModel(ProjectCreateDto projectCreateDto, Client client){
        return new Project()
                .setName(projectCreateDto.getName())
                .setClient(client);

    }
}
