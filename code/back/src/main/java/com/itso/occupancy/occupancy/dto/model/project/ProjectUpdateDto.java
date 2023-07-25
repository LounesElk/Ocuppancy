package com.itso.occupancy.occupancy.dto.model.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProjectUpdateDto {
    private String name;
    private  Long id_client;
}
