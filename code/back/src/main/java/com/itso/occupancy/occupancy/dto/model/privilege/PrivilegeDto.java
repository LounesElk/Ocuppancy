package com.itso.occupancy.occupancy.dto.model.privilege;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class PrivilegeDto {
    private Long id;
    private String description;
    private  String name;
}
