package cl.coopeuch.task_maintainer.controller;

import cl.coopeuch.task_maintainer.domain.TaskDTO;
import cl.coopeuch.task_maintainer.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/task-maintainer")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/get-all-task")
    public ResponseEntity<List<TaskDTO>> getAllTask(){
        log.info("Get all short clients");
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PostMapping("/save-task")
    public ResponseEntity<TaskDTO> saveOrUpdateTask(TaskDTO task){
        return new ResponseEntity<>(taskService.saveOrUpdateTask(task), HttpStatus.OK);
    }

    @DeleteMapping("/delete-task")
    public ResponseEntity<Void> deleteTask(TaskDTO task){
        taskService.deleteTask(task);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
