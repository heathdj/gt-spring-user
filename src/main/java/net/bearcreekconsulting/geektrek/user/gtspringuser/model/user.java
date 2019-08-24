package net.bearcreekconsulting.geektrek.user.gtspringuser.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by David on 8/23/2019. Copyright (c) 2019 Bear Creek Consulting LLC.
 */
@Data
@Entity
@Table(name = "user")
public class user implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // TODO: Make this a UUID
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
