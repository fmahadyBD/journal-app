package com.fmahadybd.journalApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing a User entry.
 */
@Entity
@Data
@Getter
@Setter
public class UserEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    /**
    * mappedBy="user" means it mapped with JournalEntry's user.
     * cascade =CascadeType.ALL  means persist, merge, remove etc applied to UserEntry are cascaded to its associated JournalEntry instances.
     * orphanRemoval =true means Automatically removes JournalEntry records when they are removed from the allJournalEntry list in UserEntry
    **/
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<JournalEntry> allJournalEntry;

}
