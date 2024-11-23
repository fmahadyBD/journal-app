package com.fmahadybd.journalApp.service;

import com.fmahadybd.journalApp.entity.JournalEntry;
import com.fmahadybd.journalApp.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for journal entry business logic.
 */
@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    /**
     * Retrieve all journal entries.
     *
     * @return List of journal entries.
     */
    public List<JournalEntry> getAll() {
        return journalRepository.findAll();
    }

    /**
     * Retrieve a journal entry by ID.
     *
     * @param id ID of the journal entry.
     * @return Optional containing the journal entry, if found.
     */
    public Optional<JournalEntry> getById(long id) {
        return journalRepository.findById(id);
    }

    /**
     * Save a journal entry.
     *
     * @param journalEntry The journal entry to save.
     */
    public void saveEntry(JournalEntry journalEntry) {
        journalRepository.save(journalEntry);
    }

    /**
     * Delete a journal entry by ID.
     *
     * @param id ID of the journal entry to delete.
     */
    public void delete(long id) {
        journalRepository.deleteById(id);
    }

    /**
     * Find a journal entry by ID.
     *
     * @param id ID of the journal entry.
     * @return Optional containing the journal entry.
     */
    public Optional<JournalEntry> findById(Long id) {
        return journalRepository.findById(id);
    }


}

