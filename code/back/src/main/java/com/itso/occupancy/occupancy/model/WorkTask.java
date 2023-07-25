package com.itso.occupancy.occupancy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "task",
        indexes = {
                @Index(columnList = "id_feature", name = "ix_feature"),
                @Index(columnList = "id_user", name = "ix_user"),
                @Index(columnList = "id_tag", name = "ix_tag"),
                @Index(columnList = "id_project", name = "ix_project")
        })
public class WorkTask extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double workTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date workDay;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name="id_feature")
    private Feature feature ;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_tag")
    private Tag tag;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name="id_project")
    private Project project;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default false") // Default value
    private boolean 				supprimer 				= false;

    @Column
    private String commentaire ;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default false") // Default value
    private boolean 				vide 				= false;

}
