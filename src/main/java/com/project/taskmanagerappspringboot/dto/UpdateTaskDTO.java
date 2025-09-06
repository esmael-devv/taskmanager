package com.project.taskmanagerappspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class UpdateTaskDTO {
    String description;
    String deadline;
    boolean completed;
}
