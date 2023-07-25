package com.itso.occupancy.occupancy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "users",
        indexes = {
        @Index(columnList = "username",name = "ix_username"),
        @Index(columnList = "id_job",name = "ix_job"),
})

public class User extends Auditable implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long                    id;

    @Column(nullable = false)
    private String                  firstName;

    @Column(nullable = false)
    private String                  lastName;

    @Column(nullable = false)
    private String                  email;

    @Column(unique = true)
    private String 	                username;

    @NotNull
    @Column(nullable = false)
    private String                  password;

    @Column
    private Instant                 lastConnection;

    @Column
    private Instant                 lastFailedAttempt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_job")
    private Job                     job;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<WorkTask> workTasks;

    @Column(columnDefinition = "integer default 0")
    @Min(value = 0)
    @Max(value = 5)
    private Integer                 numberFailedAttempt = 0;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="id_role")
    private Role 					role;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default false") // Default value
    private boolean 				isDeleted 				= false;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default true") // Default value
    private boolean 				isAccountNonExpired 	= true;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default true") // Default value
    private boolean 				isAccountNonLocked 		= true;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default true") // Default value
    private boolean 				isCredentialsNonExpired = true;

    @NotNull
    @Column(nullable = false, columnDefinition = "boolean default true") // Default value
    private boolean 				isEnabled 				= true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (this.getRole() != null){
            this.getRole().getPrivileges().stream()
                    .map(Privilege::getName)
                    .map(SimpleGrantedAuthority::new)
                    .forEach(authorities::add);

            authorities.add(new SimpleGrantedAuthority(this.getRole().getName()));

        }
        return authorities;


    }
}
