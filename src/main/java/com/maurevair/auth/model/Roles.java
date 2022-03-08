package com.maurevair.auth.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="ROLE")
public class Roles {

    @Id
    @Column(name="ID",unique=true,nullable=false)
    private String id = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name="USER_ROLE",nullable = false)
    private UserRole role;

    @ManyToMany(mappedBy = "roles")
    private List<Users> users;

}
