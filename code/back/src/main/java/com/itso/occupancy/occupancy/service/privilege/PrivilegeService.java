package com.itso.occupancy.occupancy.service.privilege;

import com.itso.occupancy.occupancy.dto.model.privilege.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface PrivilegeService {
    public ResponseEntity<List<PrivilegeDto>> getAllPrivileges();
    public ResponseEntity<PrivilegeDto> getPrivilege(Long id);
    public ResponseEntity<List<PrivilegeDto>> postAllPrivileges();
    public ResponseEntity<PrivilegeDto> postPrivilege(PrivilegeIdDto privilegeIdDto);
}
