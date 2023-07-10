package cl.coopeuch.task_maintainer.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "task")
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "active")
    private Boolean active;

}
