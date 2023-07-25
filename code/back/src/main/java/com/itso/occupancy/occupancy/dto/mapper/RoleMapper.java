package com.itso.occupancy.occupancy.dto.mapper;
import com.itso.occupancy.occupancy.dto.model.role.*;
import  com.itso.occupancy.occupancy.model.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RoleMapper {
    public  static RoleDto toRoleDto(Role role){
        return new RoleDto().setId(role.getId()).setName(role.getName());
    }
    public static RoleCreateDto toRoleCreateDto(Role role){
        return new RoleCreateDto().setName(role.getName());
    }
    public static RoleUpdateDto toRoleUpdateDto(Role role){
        return new RoleUpdateDto().setName(role.getName());
    }
    public static Role toRoleModel(RoleCreateDto roleCreateDto){
        return new Role().setName(roleCreateDto.getName());
    }
}
