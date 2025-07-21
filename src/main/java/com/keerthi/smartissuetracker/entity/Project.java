package com.keerthi.smartissuetracker.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Data
@Table(name = "projects")

@NoArgsConstructor
@AllArgsConstructor

public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private User createdBy;
}
