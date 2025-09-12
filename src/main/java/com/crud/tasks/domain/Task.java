package com.crud.tasks.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String title;
    @Column(name = "description")
    private String content;
}