package com.minsang8332.workbenchapi.entities;

import com.minsang8332.workbenchapi.constants.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(schema = "user_role")
@Entity
@Getter
@Setter
public class Role {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private UserRoleEnum name;
}
