package cl.coopeuch.task_maintainer.persistence.entities;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "task")
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creation_user")
    private String creationUser;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "active")
    private Boolean active;

}
