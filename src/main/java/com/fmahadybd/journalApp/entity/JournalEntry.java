package com.fmahadybd.journalApp.entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entity representing a journal entry.
 */
@Entity
@Data
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String content;

    private LocalDateTime date;
}
