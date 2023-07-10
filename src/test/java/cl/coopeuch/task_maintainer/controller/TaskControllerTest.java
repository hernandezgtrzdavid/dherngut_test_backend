package cl.coopeuch.task_maintainer.controller;

import cl.coopeuch.task_maintainer.domain.TaskDTO;
import cl.coopeuch.task_maintainer.fixture.TaskFixtures;
import cl.coopeuch.task_maintainer.service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.coyote.Response;
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

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Test
    public void getEmptyTaskListTest(){
        Mockito.when(taskService.getAllTasks()).thenReturn(new ArrayList<>());
        ResponseEntity<List<TaskDTO>> result = taskController.getAllTask();
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(0, result.getBody().size());

    }

    @Test
    public void getNotEmptyTaskListTest(){
        Mockito.when(taskService.getAllTasks()).thenReturn(TaskFixtures.getNotEmptyTaskDTOList());
        ResponseEntity<List<TaskDTO>> result = taskController.getAllTask();
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(3, result.getBody().size());

    }

    @Test
    public void saveOrUpdateTaskTest() {
        TaskDTO input = TaskDTO.builder()
                    .taskId(1)
                    .description("Task Especial")
                    .creationDate(new Date())
                    .activeTask(false)
                .build();

        Mockito.when(taskService.saveOrUpdateTask(Mockito.any(TaskDTO.class))).thenReturn(TaskFixtures.getValidTaskDTO());
        ResponseEntity<TaskDTO> result = taskController.saveOrUpdateTask(input);
        Assertions.assertNotNull(result.getBody());

    }

    @Test
    public void deleteTaskTest() {

        Mockito.doNothing().when(taskService).deleteTask(Mockito.anyLong());
        ResponseEntity<Void> result = taskController.deleteTask(1L);
        Assertions.assertEquals(200, result.getStatusCode().value());

    }
}
