package ru.powergleb.task.tracker.backend.tasklogic.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.powergleb.task.tracker.backend.security.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "header")
    private String header;

    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "status_active")
    private boolean statusActive;

    @Column(name = "modified")
    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;
}
