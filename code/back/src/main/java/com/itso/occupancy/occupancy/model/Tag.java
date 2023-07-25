package com.itso.occupancy.occupancy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "tag")
public class Tag extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int code;

    @JsonManagedReference
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<WorkTask> workTasks;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default false") // Default value
    private Boolean supprimer= false;
}
