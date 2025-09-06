package com.project.taskmanagerappspringboot.service;

import com.project.taskmanagerappspringboot.entities.TaskEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId =1;
    private SimpleDateFormat deadlineFormatter =  new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadlineFormatter.parse(deadline));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;
    }

    public ArrayList<TaskEntity> getTasks(){
        return tasks;
    }

    public TaskEntity getTasksById(int id){
        for(TaskEntity task : tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int taskId, String description, String deadline, boolean completed) throws ParseException{
        TaskEntity task = getTasksById(taskId);
        if(task == null){
            return null;
        }
        if(description != null) {
            task.setDescription(description);
        }
        if(deadline != null) {
            task.setDeadline(deadlineFormatter.parse(deadline));
        }
        if(completed) {
            task.setCompleted(completed);
        }
        return task;
    }
}
