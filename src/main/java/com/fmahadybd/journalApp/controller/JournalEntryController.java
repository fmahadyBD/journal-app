package com.fmahadybd.journalApp.controller;

import com.fmahadybd.journalApp.entity.JournalEntry;
import com.fmahadybd.journalApp.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Controller for managing journal entries.
 * Exposes REST APIs for CRUD operations.
 */
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalService journalService;

    /**
     * Retrieve all journal entries.
     *
     * @return ResponseEntity containing the list of entries or NOT_FOUND status.
     */
    @GetMapping("/journal")
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> allEntries = journalService.getAll();
        return allEntries.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(allEntries, HttpStatus.OK);
    }

    /**
     * Create a new journal entry.
     *
     * @param journalEntry The journal entry to be created.
     * @return ResponseEntity containing the created entry or BAD_REQUEST status in case of error.
     */
    @PostMapping("/create-journal")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry) {
        try {
//            journalEntry.setDate(LocalDateTime.now());
            journalService.saveEntry(journalEntry);
            System.out.println("In controller");
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieve a journal entry by its ID.
     *
     * @param id The ID of the journal entry.
     * @return ResponseEntity containing the journal entry or NOT_FOUND status if not found.
     */
    @GetMapping("/journal/id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable Long id) {
        return journalService.getById(id)
                .map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing journal entry by ID.
     *
     * @param newEntry Updated journal entry data.
     * @param id       The ID of the entry to update.
     * @return ResponseEntity containing the updated entry or NOT_FOUND status if entry not found.
     */
    @PutMapping("/update-journal/id/{id}")
    public ResponseEntity<JournalEntry> updateById(
            @RequestBody JournalEntry newEntry,
            @PathVariable Long id) {

        return journalService.findById(id)
                .map(existingEntry -> {
                    existingEntry.setTitle(
                            Optional.ofNullable(newEntry.getTitle())
                                    .filter(title -> !title.isBlank())
                                    .orElse(existingEntry.getTitle()));

                    existingEntry.setContent(
                            Optional.ofNullable(newEntry.getContent())
                                    .filter(content -> !content.isBlank())
                                    .orElse(existingEntry.getContent()));

                    journalService.saveEntry(existingEntry);
                    return new ResponseEntity<>(existingEntry, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a journal entry by ID.
     *
     * @param id The ID of the entry to delete.
     * @return ResponseEntity with NO_CONTENT status.
     */
    @DeleteMapping("/delete-journal/id/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        if (journalService.findById(id).isPresent()) {
            journalService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
