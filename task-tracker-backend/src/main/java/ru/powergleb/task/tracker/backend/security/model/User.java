package ru.powergleb.task.tracker.backend.security.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.powergleb.task.tracker.backend.tasklogic.model.Task;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private int id;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Must be in email format")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Column(name = "password")
    private String password;

    @ApiModelProperty(hidden = true)
    @Column(name = "role")
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @ApiModelProperty(hidden = true)
    private List<Task> tasks;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
