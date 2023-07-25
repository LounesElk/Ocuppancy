package com.itso.occupancy.occupancy.dto.model.tag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class TagUpdateDto {
    private int code;
    private String name;
}
