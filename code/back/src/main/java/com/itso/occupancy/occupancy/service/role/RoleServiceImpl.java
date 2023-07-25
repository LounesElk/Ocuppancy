package com.itso.occupancy.occupancy.service.role;

import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.dto.mapper.RoleMapper;
import com.itso.occupancy.occupancy.dto.model.role.RoleCreateDto;
import com.itso.occupancy.occupancy.dto.model.role.RoleDto;
import com.itso.occupancy.occupancy.dto.model.role.RoleIdDto;
import com.itso.occupancy.occupancy.dto.model.role.RoleUpdateDto;
import com.itso.occupancy.occupancy.model.Role;
import com.itso.occupancy.occupancy.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        List<RoleDto> allRolesDto = roleRepository.SupprimerIsFalse().stream().map(RoleMapper::toRoleDto).toList();
        return new ResponseEntity<>(allRolesDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RoleDto> getRole (Long id){
        RoleDto roleDto = RoleMapper.toRoleDto(findRoleIfExists(id));
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RoleDto>> postAllRoles(){
        List<RoleDto> allRolesDto = roleRepository.SupprimerIsFalse().stream().map(RoleMapper::toRoleDto).toList();
        return new ResponseEntity<>(allRolesDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RoleDto> postRole (RoleIdDto roleIdDto){
        RoleDto roleDto = RoleMapper.toRoleDto(findRoleIfExists(roleIdDto.getId()));
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RoleDto> createRole(RoleCreateDto roleCreateDto){
        Role role = RoleMapper.toRoleModel(roleCreateDto);
        Role role1 = roleRepository.save(role);
        RoleDto roleDto = RoleMapper.toRoleDto(role1);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteRole(Long id){
        Role role =findRoleIfExists(id);
        role.setSupprimer(Boolean.TRUE);
        roleRepository.save(role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<RoleDto> updateRole(Long id, RoleUpdateDto roleUpdateDto){
        Role role = findRoleIfExists(id);
        role.setName(roleUpdateDto.getName());
        Role role1 = roleRepository.save(role);
        RoleDto roleDto = RoleMapper.toRoleDto(role1);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    private Role findRoleIfExists(Long id) {

        return roleRepository.findByIdAndSupprimerIsFalse(id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find User [id = %s]", id)
                ));
    }

}
