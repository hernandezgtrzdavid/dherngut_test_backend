package cl.coopeuch.task_maintainer.persistence.repositories;

import cl.coopeuch.task_maintainer.persistence.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>, JpaSpecificationExecutor<TaskRepository> {

}
