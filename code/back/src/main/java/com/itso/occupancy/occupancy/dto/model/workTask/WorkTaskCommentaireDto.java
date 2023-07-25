package com.itso.occupancy.occupancy.dto.model.workTask;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class WorkTaskCommentaireDto {
    private Long id;
    private String commentaire;
}
