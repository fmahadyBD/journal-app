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
    private String title;
    private String content;
    private LocalDateTime date;

    /**
     * @JoinColumn(name ="user_id") means Specifies the name of the foreign Key Column in the JournalEntry table
     * Ensures each JournalEntry is linked to a specific UserEntry
     **/
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntry user;
}
