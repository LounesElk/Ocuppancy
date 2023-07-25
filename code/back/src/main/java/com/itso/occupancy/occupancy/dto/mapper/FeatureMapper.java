package com.itso.occupancy.occupancy.dto.mapper;

import com.itso.occupancy.occupancy.dto.model.feature.*;
import com.itso.occupancy.occupancy.dto.model.user.UserDto;
import com.itso.occupancy.occupancy.model.Feature;
import com.itso.occupancy.occupancy.model.Project;
import lombok.AccessLevel;
import java.util.Date;
import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeatureMapper {
    public static FeatureDto toFeatureDto(Feature feature){
        return new FeatureDto()
                .setId(feature.getId())
                .setCode(feature.getCode())
                .setDate_E(feature.getDate_E())
                .setTemps_E(feature.getTemps_E())
                .setName(feature.getName())
                .setId_project(feature.getProject().getId());
    }

    public static FeatureDto toFeatureDto(Feature feature,Date date, double temps){
        return new FeatureDto()
                .setId(feature.getId())
                .setCode(feature.getCode())
                .setDate_E(feature.getDate_E())
                .setTemps_E(feature.getTemps_E())
                .setName(feature.getName())
                .setId_project(feature.getProject().getId())
                .setDate_R(date)
                .setTemps_R(temps);
    }

    public static FeatureUserDto toFeatureUserDto(Feature feature,Date date, double temps, List<UserDto> user){
        return new FeatureUserDto()
                .setId(feature.getId())
                .setCode(feature.getCode())
                .setDate_E(feature.getDate_E())
                .setTemps_E(feature.getTemps_E())
                .setName(feature.getName())
                .setId_project(feature.getProject().getId())
                .setDate_R(date)
                .setTemps_R(temps)
                .setUser(user);
    }

    public static FeatureCreateDto ToFeatureCreateDto(Feature feature){
        return new FeatureCreateDto()
                .setCode(feature.getCode())
                .setName(feature.getName())
                .setId_project(feature.getProject().getId());
    }
    public static FeatureUpdateDto ToFeatureUpdateDto(Feature feature){
        return new FeatureUpdateDto()
                .setCode(feature.getCode())
                .setName(feature.getName())
                .setId_project(feature.getProject().getId());
    }
    public static Feature toFeatureModel(FeatureCreateDto featureCreateDto,Project project){
        return new Feature()
                .setCode(featureCreateDto.getCode())
                .setName(featureCreateDto.getName())
                .setDate_E(featureCreateDto.getDate_E())
                .setTemps_E(featureCreateDto.getTemps_E())
                .setProject(project);
    }

}
