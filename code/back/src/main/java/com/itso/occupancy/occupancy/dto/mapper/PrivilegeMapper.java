package com.itso.occupancy.occupancy.dto.mapper;

import com.itso.occupancy.occupancy.dto.model.privilege.PrivilegeDto;
import com.itso.occupancy.occupancy.model.Privilege;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PrivilegeMapper {

    public  static PrivilegeDto toPrivilegeDto(Privilege privilege){
        return new PrivilegeDto().setId(privilege.getId()).setDescription(privilege.getDescription()).setName(privilege.getName());

    }
}
