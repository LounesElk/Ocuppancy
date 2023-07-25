package com.itso.occupancy.occupancy.controller.workTask;

import com.itso.occupancy.occupancy.dto.model.workTask.*;
import com.itso.occupancy.occupancy.model.WorkTask;
import com.itso.occupancy.occupancy.service.workTask.WorkTaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
@Slf4j

public class WorkTaskController {
    private  final WorkTaskService workTaskService;
    @GetMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskWithoutDto>> getAllWorkTasks() { //Récupère Toutes les Task
        return workTaskService.getAllWorkTasks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<WorkTaskDto> getWorkTasks(@PathVariable Long id){ //Récupère une Task
        return workTaskService.getWorkTask(id);
    }

    @GetMapping("/time/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> getAllWorkTaskByWorkTime(@PathVariable int id){ //Récupère Toutes les Task pour une heure donnée
        return workTaskService.getAllWorkTaskByWorkTime(id);
    }

    @GetMapping("/User/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskWithoutDto>> getAllWorkTaskByIdUser(@PathVariable Long id){ //Récupère Toutes les Task pour un utilisateur donné
        return workTaskService.getAllWorkTaskByIdUser(id);
    }

    @PostMapping("/date")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskWithoutDto>> postWorkTaskByDate(@RequestBody WorkTaskToDateDto workTaskToDateDto){ //Récupère une Task pour une date donnée par post
        return workTaskService.postAllWorkTaskByDate(workTaskToDateDto);
    }

    @GetMapping("/project/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> getAllWorkTaskByProject(@PathVariable Long id){ //Récupère Toutes les Task pour un project donné
        return workTaskService.getAllWorkTaskByProject(id);
    }

    @GetMapping("/tag/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> getAllWorkTaskByTag(@PathVariable Long id){ //Récupère Toutes les Task pour un tag donné
        return workTaskService.getAllWorkTaskByTag(id);
    }

    @PostMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> postAllWorkTasks() { //Récupère Toutes les Task
        return workTaskService.postAllWorkTasks();
    }

    @PostMapping("/id")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<WorkTaskDto> postWorkTask(@RequestBody WorkTaskIdDto workTaskIdDto){ //Récupère une Task par post
        return workTaskService.postWorkTask(workTaskIdDto);
    }

    @PostMapping("/time")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> postAllWorkTaskByWorkTime(@RequestBody WorkTaskWorkTimeDto workTaskWorkTimeDto){ //Récupère Toutes les Task pour une heure donnée par post
        return workTaskService.postAllWorkTaskByWorkTime(workTaskWorkTimeDto);
    }

    @PostMapping("/User")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> postAllWorkTaskByIdUser(@RequestBody WorkTaskIdUserDto workTaskIdUserDto){ //Récupère Toutes les Task pour un utilisateur donné par post
        return workTaskService.postAllWorkTaskByIdUser(workTaskIdUserDto);
    }

    @PostMapping("/project")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> postAllWorkTaskByProject(@RequestBody WorkTaskIdProjectDto workTaskIdProjectDto){ //Récupère Toutes les Task pour un project donné par post
        return workTaskService.postAllWorkTaskByProject(workTaskIdProjectDto);
    }

    @PostMapping("/tag")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> getAllWorkTaskByTag(@RequestBody WorkTaskIdTagDto workTaskIdTagDto){ //Récupère Toutes les Task pour un tag donné par post
        return workTaskService.postAllWorkTaskByTag(workTaskIdTagDto);
    }

    @PostMapping("/create")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<WorkTaskDto> createNewTask(@RequestBody WorkTaskCreateDto workTask){ //Crée une nouvelle Task à partir des données transmit par méthode post
        return workTaskService.createNewTask(workTask);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<WorkTaskDto> deleteWorkTask(@PathVariable Long id){ //Supprime une Task à partir d'un id en GET(DELETE)
        return workTaskService.deleteWorkTask(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<WorkTaskDto> uptadeWorktask(@PathVariable Long id, @RequestBody WorkTaskUptadeDto workTaskUptadeDto){ //Update une Task à partir d'un id en PUT
        return  workTaskService.updateWorkTask(id, workTaskUptadeDto);
    }

    @GetMapping("/feature/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> getAllWorkTaskByIdFeature(@PathVariable Long id){ //Récupère Toutes les Task pour une feature donné
        return workTaskService.getAllWorkTaskByIdFeature(id);
    }

    @PostMapping("/feature")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> postAllWorkTaskByIdFeature(@RequestBody WorkTaskIdFeatureDto workTaskIdFeatureDto){ //Récupère Toutes les Task pour une feature donné par post
        return workTaskService.postAllWorkTaskByIdFeature(workTaskIdFeatureDto);
    }

    @PostMapping("/commentaire")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<WorkTaskWithoutDto> postAddCommentaire(@RequestBody WorkTaskCommentaireDto workTaskCommentaireDto){ //Ajoute un commentaire
        return  workTaskService.postAddCommentaire(workTaskCommentaireDto);
    }

    @PostMapping("/createWithout")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<WorkTaskCreateWithoutDto> createNewTaskWithout(@RequestBody WorkTaskCreateWithoutDto workTask){ //Crée une nouvelle Task à partir des données sans un project et une feature transmit par méthode post
        return workTaskService.createNewTaskWithout(workTask);
    }

    @GetMapping("/without/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
        public ResponseEntity<WorkTaskWithoutDto> getWorkTaskWithout(@PathVariable Long id){ //Récupère une Task mais sans les valeur de project et feature car elles sont null
        return workTaskService.getWorkTaskWithout(id);
    }

    @GetMapping("/vide/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<WorkTaskVideDto> getWorkTaskVide(@PathVariable Long id){ //Verifie si la task est vide ou pas
        return workTaskService.getWorkTaskVide(id);
    }

    @PutMapping("/time/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<WorkTaskWithoutDto> putChangeWorkTime(@PathVariable Long id, @RequestBody WorkTaskWorkTimeDto workTaskWorkTimeDto){ //Permet de changer la durée d'une task
        return  workTaskService.putChangeWorkTime(id, workTaskWorkTimeDto);
    }

    @GetMapping("/videFalse/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskDto>> getAllWorkTaskVideFalseByUser(@PathVariable Long id) { //Récupère Toutes les Task qui ont la valuer faux à vide pour un user
        return workTaskService.getAllWorkTaskVideFalseByUser(id);
    }

    @PostMapping("/calculate")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<WorkTaskCalculateTimeDto> postCalculateHeure(@RequestBody WorkTaskToDateDto workTaskToDateDto){ //Calcule le nombre d'heure pour une task
        return workTaskService.postCalculateHeure(workTaskToDateDto);
    }

    @GetMapping("/commentaire/liste")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<WorkTaskWithoutDto>> postLastCommentaire() { //Récupère Toutes les Task qui ont un commentaire
        return workTaskService.postLastCommentaire();
    }
}
