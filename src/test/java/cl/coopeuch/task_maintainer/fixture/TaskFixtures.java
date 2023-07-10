package cl.coopeuch.task_maintainer.fixture;

import cl.coopeuch.task_maintainer.domain.TaskDTO;
import cl.coopeuch.task_maintainer.persistence.entities.TaskEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskFixtures {

    public static TaskDTO getValidTaskDTO(){
        return TaskDTO.builder()
                .taskId(1)
                .description("Tarea 1")
                .creationDate(new Date())
                .activeTask(true)
                .build();
    }

    public static List<TaskDTO> getNotEmptyTaskDTOList(){
        List<TaskDTO> result =  new ArrayList<>();
        result.add(TaskDTO.builder()
                    .taskId(1)
                    .description("Tarea 1")
                    .creationDate(new Date())
                    .activeTask(true)
                .build());
        result.add(TaskDTO.builder()
                    .taskId(2)
                    .description("Tarea 2")
                    .creationDate(new Date())
                    .activeTask(false)
                .build());
        result.add(TaskDTO.builder()
                    .taskId(3)
                    .description("Tarea 3")
                    .creationDate(new Date())
                    .activeTask(true)
                .build());
        return result;
    }

    public static TaskEntity getActiveTaskEntity(){
        TaskEntity entity = new TaskEntity();
        entity.setId(1L);
        entity.setDescription("Tarea 1");
        entity.setCreationDate(new Date());
        entity.setActive(true);
        return entity;
    }

    public static TaskEntity getNoActiveTaskEntity(){
        TaskEntity entity = new TaskEntity();
        entity.setId(1L);
        entity.setDescription("Tarea 1");
        entity.setCreationDate(new Date());
        entity.setActive(false);
        return entity;
    }

    public static List<TaskEntity> getNotEmptyTaskEntityList(){
        List<TaskEntity> result =  new ArrayList<>();

        TaskEntity entity1 = new TaskEntity();
        entity1.setId(1L);
        entity1.setDescription("Tarea 1");
        entity1.setCreationDate(new Date());
        entity1.setActive(true);

        TaskEntity entity2 = new TaskEntity();
        entity2.setId(2L);
        entity2.setDescription("Tarea 2");
        entity2.setCreationDate(new Date());
        entity2.setActive(true);

        TaskEntity entity3 = new TaskEntity();
        entity3.setId(3L);
        entity3.setDescription("Tarea 3");
        entity3.setCreationDate(new Date());
        entity3.setActive(true);

        result.add(entity1);
        result.add(entity2);
        result.add(entity3);

        return result;
    }
}
