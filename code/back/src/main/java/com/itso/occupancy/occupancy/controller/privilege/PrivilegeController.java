package com.itso.occupancy.occupancy.controller.privilege;

import com.itso.occupancy.occupancy.dto.model.privilege.PrivilegeDto;
import com.itso.occupancy.occupancy.dto.model.privilege.PrivilegeIdDto;
import com.itso.occupancy.occupancy.service.privilege.PrivilegeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/privileges")
@AllArgsConstructor
@Slf4j
public class PrivilegeController {
    private final PrivilegeService privilegeService;

    @GetMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public  ResponseEntity<List<PrivilegeDto>> getAllPrivilege() {return privilegeService.getAllPrivileges();} //Récupère tous les privileges en get

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<PrivilegeDto> getPrivilege(@PathVariable Long id) {return privilegeService.getPrivilege(id);} //Récupère un privilege en get

    @PostMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public  ResponseEntity<List<PrivilegeDto>> postAllPrivileges() {return privilegeService.postAllPrivileges();} //Récupère tous les privileges en post

    @PostMapping("/id")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<PrivilegeDto> postPrivilege(@RequestBody PrivilegeIdDto privilegeIdDto) {return privilegeService.postPrivilege(privilegeIdDto);} //Récupère un privilege en post
}

