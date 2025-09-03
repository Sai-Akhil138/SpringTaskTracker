package com.tasktracker.task_tracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_STARTED;

}
