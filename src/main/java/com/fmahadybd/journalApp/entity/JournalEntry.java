package com.fmahadybd.journalApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonBackReference
    private UserEntry user;

    // Automatically set the date field before persisting
    @PrePersist
    public void prePersist() {
        this.date = LocalDateTime.now();
    }
}
