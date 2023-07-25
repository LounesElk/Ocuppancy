package com.itso.occupancy.occupancy.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
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
@Table(name = "user_role")
public class Role implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                id;

    private String              name;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default false") // Default value
    private Boolean supprimer= false;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "role")
    private Collection<User>    users;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(name 				= "roles_privileges",
            joinColumns 			= @JoinColumn(name = "role_id"		, referencedColumnName = "id"),
            inverseJoinColumns 	    = @JoinColumn(name = "privilege_id"	, referencedColumnName = "id"))
    private Collection<Privilege> privileges;




}
