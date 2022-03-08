package com.maurevair.auth.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="USERS")
public class Users {
    @Id
    @Column(name="USER_ID",nullable=false,unique = true)
    private String userId = UUID.randomUUID().toString();

    @NotNull
    @Column(name="USERNAME",nullable = false,length = 12)
    @Max(value = 12)
    private String userName;

    @NotNull
    @Email
    @Column(name="EMAIL",nullable = false)
    private String email;

    @NotNull
    @Column(name="PASSWORD",nullable = false)
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="USER_ROLES",joinColumns = @JoinColumn(name="USERS_USER_ID"),
    inverseJoinColumns = @JoinColumn(name="ROLE_ID"))
    private List<Roles> roles = new ArrayList<>();

}
