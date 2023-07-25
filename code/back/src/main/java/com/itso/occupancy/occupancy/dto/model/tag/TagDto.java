package com.itso.occupancy.occupancy.dto.model.tag;

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
public class TagDto {
    private Long id;
    private int code;
    private String name;
}
