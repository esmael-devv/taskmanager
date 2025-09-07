package com.project.taskmanagerappspringboot.dto;

import lombok.*;


@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CreateNoteDTO {
    private String title;
    private String body;
}
