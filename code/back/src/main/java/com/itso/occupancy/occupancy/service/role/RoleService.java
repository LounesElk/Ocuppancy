package com.itso.occupancy.occupancy.service.role;


import com.itso.occupancy.occupancy.dto.model.role.*;
import com.itso.occupancy.occupancy.dto.model.tag.TagUpdateDto;
import com.itso.occupancy.occupancy.model.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface RoleService {
    public ResponseEntity<List<RoleDto>> getAllRoles();
    public ResponseEntity<List<RoleDto>> postAllRoles();
    public  ResponseEntity<RoleDto> getRole(Long id);
    public  ResponseEntity<RoleDto> postRole(RoleIdDto roleIdDto);
    public ResponseEntity<RoleDto> createRole(RoleCreateDto roleCreateDto);
    ResponseEntity<Object> deleteRole(Long id);
    ResponseEntity<RoleDto> updateRole(Long id, RoleUpdateDto roleUpdateDto);
}
