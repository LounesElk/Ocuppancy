package com.itso.occupancy.occupancy.dto.mapper;
import com.itso.occupancy.occupancy.dto.model.workTask.*;
import com.itso.occupancy.occupancy.model.*;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class WorkTaskMapper {
    public static WorkTaskDto toworkTaskDto(WorkTask workTask){
        return new WorkTaskDto()
                .setId(workTask.getId())
                .setWork_day( workTask.getWorkDay())
                .setWork_time(workTask.getWorkTime())
                .setId_feature(workTask.getFeature().getId())
                .setId_project(workTask.getProject().getId())
                .setId_tag(workTask.getTag().getId())
                .setId_user(workTask.getUser().getId())
                .setCommentaire(workTask.getCommentaire());
    }

    public static WorkTaskDto toworkTaskDto(WorkTask workTask, Double duree){
        return new WorkTaskDto()
                .setId(workTask.getId())
                .setWork_day( workTask.getWorkDay())
                .setWork_time(workTask.getWorkTime())
                .setId_feature(workTask.getFeature().getId())
                .setId_project(workTask.getProject().getId())
                .setId_tag(workTask.getTag().getId())
                .setId_user(workTask.getUser().getId())
                .setCommentaire(workTask.getCommentaire())
                .setDuree(duree);
    }

    public static WorkTaskCreateDto toWorkTaskCreateDto(WorkTask workTask){
        return new WorkTaskCreateDto()
                .setWork_day( workTask.getWorkDay())
                .setWork_time(workTask.getWorkTime())
                .setId_feature(workTask.getFeature().getId())
                .setId_project(workTask.getProject().getId())
                .setId_tag(workTask.getTag().getId())
                .setId_user(workTask.getUser().getId());
    }

    public static WorkTaskUptadeDto toWorkTaskUptadeDto(WorkTask workTask){
        return new WorkTaskUptadeDto()
                .setWork_day( workTask.getWorkDay())
                .setWork_time(workTask.getWorkTime())
                .setId_feature(workTask.getFeature().getId())
                .setId_project(workTask.getProject().getId())
                .setId_tag(workTask.getTag().getId())
                .setId_user(workTask.getUser().getId());
    }

    public static WorkTask toWorkTaskModel(WorkTaskCreateDto workTaskCreateDto,Project project,Tag tag, User user, Feature feature){
        return new WorkTask()
                .setWorkDay(workTaskCreateDto.getWork_day())
                .setWorkTime(workTaskCreateDto.getWork_time())
                .setFeature(feature)
                .setProject(project)
                .setTag(tag)
                .setUser(user);
    }
    public static WorkTask toWorkTaskModelWithout(WorkTaskCreateWithoutDto workTaskCreateDto, Tag tag, User user, Boolean vide){
        return new WorkTask()
                .setWorkDay(workTaskCreateDto.getWork_day())
                .setWorkTime(workTaskCreateDto.getWork_time())
                .setTag(tag)
                .setUser(user)
                .setVide(vide);
    }

    public static WorkTaskWithoutDto toWorkTaskWithoutDto(WorkTask workTask){
        return  new WorkTaskWithoutDto()
                .setId(workTask.getId())
                .setWork_day( workTask.getWorkDay())
                .setWork_time(workTask.getWorkTime())
                .setId_tag(workTask.getTag().getId())
                .setId_user(workTask.getUser().getId())
                .setCommentaire(workTask.getCommentaire())
                .setVide(workTask.isVide());
    }

    public static WorkTaskWithoutDto toWorkTaskWithoutDto(WorkTask workTask, Double Duree){
        return  new WorkTaskWithoutDto()
                .setId(workTask.getId())
                .setWork_day( workTask.getWorkDay())
                .setWork_time(workTask.getWorkTime())
                .setId_tag(workTask.getTag().getId())
                .setId_user(workTask.getUser().getId())
                .setCommentaire(workTask.getCommentaire())
                .setVide(workTask.isVide())
                .setDuree(Duree);
    }

    public static WorkTaskVideDto toWorkTaskVideDto(WorkTask workTask){
        return  new WorkTaskVideDto()
                .setVide(workTask.isVide());
    }

    public static WorkTaskCalculateTimeDto toWorkTaskCalculateTimeDto(WorkTaskToDateDto workTask, double duree) {
        return  new WorkTaskCalculateTimeDto()
                .setWork_day(workTask.getWork_day())
                .setWork_time(duree)
                .setId_user(workTask.getId_user());
    }
}
