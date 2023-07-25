package com.itso.occupancy.occupancy.dto.model.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ClientCreateDto {
    private String name;
}
