package com.project.taskmanagerappspringboot.controllers;

import com.project.taskmanagerappspringboot.dto.CreateNoteDTO;
import com.project.taskmanagerappspringboot.dto.CreateNotesResponseDTO;
import com.project.taskmanagerappspringboot.entities.NoteEntity;
import com.project.taskmanagerappspringboot.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskid}/notes")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskid") Integer taskid) {
        var notes = noteService.getNoteForTask(taskid);

        return  ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNotesResponseDTO> addNote(@PathVariable("taskid") Integer taskid, @RequestBody CreateNoteDTO body) {
        var note = noteService.addNoteForTask(taskid, body.getTitle(), body.getBody());

        return ResponseEntity.ok(new CreateNotesResponseDTO(taskid, note));
    }


}
