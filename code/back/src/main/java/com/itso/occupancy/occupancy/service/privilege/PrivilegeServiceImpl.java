package com.itso.occupancy.occupancy.service.privilege;

import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.dto.mapper.PrivilegeMapper;
import com.itso.occupancy.occupancy.dto.model.privilege.*;
import com.itso.occupancy.occupancy.model.Privilege;
import com.itso.occupancy.occupancy.repository.PrivilegeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService{
    private final PrivilegeRepository privilegeRepository;
    @Override
    public ResponseEntity<List<PrivilegeDto>> getAllPrivileges() {
        List<PrivilegeDto> allPrivilegesDto = privilegeRepository.findAll()
                .stream()
                .map(PrivilegeMapper::toPrivilegeDto)
                .toList();
        return new ResponseEntity<>(allPrivilegesDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PrivilegeDto> getPrivilege(Long id){
        PrivilegeDto privilegeDto = PrivilegeMapper.toPrivilegeDto(findPrivilegeIfExists(id));
        return new ResponseEntity<>(privilegeDto, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<PrivilegeDto>> postAllPrivileges() {
        List<PrivilegeDto> allPrivilegesDto = privilegeRepository.findAll()
                .stream()
                .map(PrivilegeMapper::toPrivilegeDto)
                .toList();
        return new ResponseEntity<>(allPrivilegesDto, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PrivilegeDto> postPrivilege(PrivilegeIdDto privilegeIdDto){
        PrivilegeDto privilegeDto = PrivilegeMapper.toPrivilegeDto(findPrivilegeIfExists(privilegeIdDto.getId()));
        return new ResponseEntity<>(privilegeDto, HttpStatus.OK);
    }

    private Privilege findPrivilegeIfExists(Long id) {

        return privilegeRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find User [id = %s]", id)
                ));
    }
}
