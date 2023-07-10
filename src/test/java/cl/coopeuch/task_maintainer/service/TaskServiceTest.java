package cl.coopeuch.task_maintainer.service;

import cl.coopeuch.task_maintainer.domain.TaskDTO;
import cl.coopeuch.task_maintainer.fixture.TaskFixtures;
import cl.coopeuch.task_maintainer.persistence.entities.TaskEntity;
import cl.coopeuch.task_maintainer.persistence.repositories.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getEmptyTaskListTest(){
        Mockito.when(taskRepository.findAll()).thenReturn(new ArrayList<>());
        List<TaskDTO> result = taskService.getAllTasks();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());

    }

    @Test
    public void getNotEmptyTaskListTest(){
        Mockito.when(taskRepository.findAll()).thenReturn(TaskFixtures.getNotEmptyTaskEntityList());
        List<TaskDTO> result = taskService.getAllTasks();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());

    }

    @Test
    public void saveTaskTest() {
        TaskDTO input = TaskDTO.builder()
                    .taskId(1)
                    .description("Task Especial")
                    .creationDate(new Date())
                    .activeTask(false)
                .build();
        Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TaskFixtures.getActiveTaskEntity()));
        Mockito.when(taskRepository.saveAndFlush(Mockito.any(TaskEntity.class))).thenReturn(TaskFixtures.getActiveTaskEntity());
        TaskDTO result = taskService.saveOrUpdateTask(input);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Task Especial", result.getDescription());

    }

    @Test
    public void updateTaskTest() {
        TaskDTO input = TaskDTO.builder()
                .taskId(1)
                .description("Task Especial")
                .creationDate(new Date())
                .activeTask(false)
                .build();
        Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(TaskFixtures.getNoActiveTaskEntity()));
        Mockito.when(taskRepository.saveAndFlush(Mockito.any(TaskEntity.class))).thenReturn(TaskFixtures.getNoActiveTaskEntity());
        TaskDTO result = taskService.saveOrUpdateTask(input);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Task Especial", result.getDescription());
        Assertions.assertFalse(result.isActiveTask());
    }

}
