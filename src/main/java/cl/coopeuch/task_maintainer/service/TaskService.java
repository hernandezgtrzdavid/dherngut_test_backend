package cl.coopeuch.task_maintainer.service;

import cl.coopeuch.task_maintainer.domain.TaskDTO;
import cl.coopeuch.task_maintainer.persistence.entities.TaskEntity;
import cl.coopeuch.task_maintainer.persistence.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDTO> getAllTasks(){
        List<TaskEntity> dbResult = taskRepository.findAll();
        return dbResult.stream().map(
                r -> TaskDTO.builder()
                        .taskId(r.getId().intValue())
                        .creationUser(r.getCreationUser())
                        .description(r.getDescription())
                        .creationDate(r.getCreationDate())
                        .activeTask(r.getActive())
                        .build()).collect(Collectors.toList());
    }

    public TaskDTO saveOrUpdateTask(TaskDTO task){
        TaskEntity entity = getEntityFromDTO(task);
        TaskEntity taskForUpdate = taskRepository.findById(task.getTaskId().longValue()).orElse(null);
        if(null != taskForUpdate){
            taskForUpdate.setDescription(entity.getDescription());
            taskForUpdate.setActive(entity.getActive());
            taskRepository.saveAndFlush(taskForUpdate);
            return getDTOFromEntity(taskForUpdate);
        }else{
            taskRepository.saveAndFlush(entity);
            return getDTOFromEntity(entity);
        }
    }

    public void deleteTask(TaskDTO task){
        taskRepository.deleteById(task.getTaskId().longValue());
    }

    private TaskEntity getEntityFromDTO(TaskDTO task){
        TaskEntity entity = new TaskEntity();
        entity.setCreationUser(task.getCreationUser());
        entity.setDescription(task.getDescription());
        entity.setCreationDate(new Date());
        entity.setActive(task.isActiveTask());
        return entity;
    }

    private TaskDTO getDTOFromEntity(TaskEntity entity){
        return TaskDTO.builder()
                .taskId(entity.getId().intValue())
                .creationUser(entity.getCreationUser())
                .description(entity.getDescription())
                .creationDate(entity.getCreationDate())
                .activeTask(entity.getActive())
                .build();
    }

}
