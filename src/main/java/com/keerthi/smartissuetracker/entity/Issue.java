package com.keerthi.smartissuetracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "issue")
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;   // OPEN, IN_PROGRESS, DONE
    private String priority; // LOW, MEDIUM, HIGH, CRITICAL

    @ManyToOne
    private User assignedTo;

    @ManyToOne
    private Project project;

    private LocalDate createdAt;
    private LocalDate dueDate;
}
