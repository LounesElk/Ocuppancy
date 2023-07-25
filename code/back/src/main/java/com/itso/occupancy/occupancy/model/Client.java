package com.itso.occupancy.occupancy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
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
@Table(name = "client")
public class Client extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Project> projects;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default false") // Default value
    private boolean 				supprimer 				= false;
}
