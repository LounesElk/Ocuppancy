package com.itso.occupancy.occupancy.dto.model.feature;

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
public class FeatureRechercheDto {
    private String name;

    private Long id_project;
}
