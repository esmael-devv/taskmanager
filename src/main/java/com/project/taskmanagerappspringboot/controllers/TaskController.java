package com.project.taskmanagerappspringboot.controllers;

import com.project.taskmanagerappspringboot.dto.CreateTaskDTO;
import com.project.taskmanagerappspringboot.dto.ErrorResponseDTO;
import com.project.taskmanagerappspringboot.dto.UpdateTaskDTO;
import com.project.taskmanagerappspringboot.entities.TaskEntity;
import com.project.taskmanagerappspringboot.service.NoteService;
import com.project.taskmanagerappspringboot.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final NoteService noteService;

    public TaskController(TaskService taskService, NoteService noteService ) {

        this.taskService = taskService;
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        var task = taskService.getTasks();
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id) {
        var task = taskService.getTasksById(id);
        var notes = noteService.getNoteForTask(id);
        if(task == null) {
            return ResponseEntity.notFound().build();
        }
        task.setNotes(notes);

        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());

        return ResponseEntity.ok(task);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException {
        var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.isCompleted());

        if(task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e) {
        if(e instanceof ParseException) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date Format"));
        }

        e.printStackTrace();
        return ResponseEntity.badRequest().body(new ErrorResponseDTO("Internal server Error"));
    }

}
