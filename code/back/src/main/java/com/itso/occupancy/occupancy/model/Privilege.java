package com.itso.occupancy.occupancy.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "user_privileges")
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String 						name;

    private String 						description;

    @JsonManagedReference
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role>            roles;



}
