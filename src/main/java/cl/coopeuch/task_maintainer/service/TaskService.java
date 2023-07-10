package cl.coopeuch.task_maintainer.service;

import cl.coopeuch.task_maintainer.domain.TaskDTO;
import cl.coopeuch.task_maintainer.persistence.entities.TaskEntity;
import cl.coopeuch.task_maintainer.persistence.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDTO> getAllTasks(){
        List<TaskEntity> dbResult = taskRepository.findAll();
        return dbResult.stream().map(
                r -> TaskDTO.builder()
                        .taskId(r.getId().intValue())
                        .description(r.getDescription())
                        .creationDate(r.getCreationDate())
                        .activeTask(r.getActive())
                        .build()).sorted(Comparator.comparingInt(TaskDTO::getTaskId)).collect(Collectors.toList());
    }

    public TaskDTO saveOrUpdateTask(TaskDTO task){
        TaskEntity entity = getEntityFromDTO(task);
        if(null != task.getTaskId()) {
            TaskEntity taskForUpdate = taskRepository.findById(task.getTaskId().longValue()).orElse(null);
            if (null != taskForUpdate) {
                taskForUpdate.setDescription(entity.getDescription());
                taskForUpdate.setActive(entity.getActive());
                taskRepository.saveAndFlush(taskForUpdate);
                return getDTOFromEntity(taskForUpdate);

            }
        }
        taskRepository.saveAndFlush(entity);
        return getDTOFromEntity(entity);

    }

    public void deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
    }

    private TaskEntity getEntityFromDTO(TaskDTO task){
        TaskEntity entity = new TaskEntity();
        entity.setDescription(task.getDescription());
        entity.setCreationDate(new Date());
        entity.setActive(task.isActiveTask());
        return entity;
    }

    private TaskDTO getDTOFromEntity(TaskEntity entity){
        return TaskDTO.builder()
                .taskId(entity.getId().intValue())
                .description(entity.getDescription())
                .creationDate(entity.getCreationDate())
                .activeTask(entity.getActive())
                .build();
    }

}
