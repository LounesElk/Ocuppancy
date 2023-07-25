package com.itso.occupancy.occupancy.controller.role;


import com.itso.occupancy.occupancy.dto.model.role.RoleCreateDto;
import com.itso.occupancy.occupancy.dto.model.role.RoleDto;
import com.itso.occupancy.occupancy.dto.model.role.RoleIdDto;
import com.itso.occupancy.occupancy.dto.model.role.RoleUpdateDto;
import com.itso.occupancy.occupancy.service.role.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;

    @GetMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<RoleDto>> getAllRoles() { //Récupère tous les roles en get
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<RoleDto> getRole(@PathVariable Long id){ //Récupère un role en get
        return roleService.getRole(id);
    }

    @PostMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')") //Récupère tous les roles en post
    public ResponseEntity<List<RoleDto>> postAllRoles() {
        return roleService.postAllRoles();
    }

    @PostMapping("/id")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<RoleDto> postRole(@RequestBody RoleIdDto roleIdDto){ //Récupère un role en post
        return roleService.postRole(roleIdDto);
    }

    @PostMapping("/create")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleCreateDto roleCreateDto){ //Crée un role
        return roleService.createRole(roleCreateDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id){ //Supprime un role
        return roleService.deleteRole(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id,@RequestBody RoleUpdateDto roleUpdateDto){ //Modifie un role
        return roleService.updateRole(id,roleUpdateDto);
    }
}
