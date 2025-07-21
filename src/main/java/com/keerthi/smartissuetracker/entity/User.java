package com.keerthi.smartissuetracker.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String role; // ADMIN, DEV, REPORTER
}
