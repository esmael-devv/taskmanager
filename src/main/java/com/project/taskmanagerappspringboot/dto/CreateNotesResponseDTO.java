package com.project.taskmanagerappspringboot.dto;

import com.project.taskmanagerappspringboot.entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CreateNotesResponseDTO {
    private Integer taskId;
    private NoteEntity note;

}
