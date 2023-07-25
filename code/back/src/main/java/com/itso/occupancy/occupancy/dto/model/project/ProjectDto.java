package com.itso.occupancy.occupancy.dto.model.project;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
public class ProjectDto{
    private Long id;
    private String name;
    private  Long id_client;

}
