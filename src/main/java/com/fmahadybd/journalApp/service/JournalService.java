package com.fmahadybd.journalApp.service;

import com.fmahadybd.journalApp.entity.JournalEntry;
import com.fmahadybd.journalApp.repository.JournalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public boolean addNew(JournalEntry journalEntry) {
        try {
            journalRepository.save(journalEntry);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<JournalEntry> getAll() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> getById(long id) {
        return journalRepository.findById(id);
    }

    public String delete(long id) {
        try {
            journalRepository.deleteById(id);
            return "Deleted";
        } catch (Exception e) {
           return "Sorry Not possible";
        }
    }

    public JournalEntry updateById(long id, JournalEntry newJournalEntry) {
        return journalRepository.findById(id)
                .map(oldEntry -> {
                    oldEntry.setTitle(
                            newJournalEntry.getTitle() != null
                                    &&
                                    newJournalEntry.getTitle().equals("")
                                    ? newJournalEntry.getTitle()
                                    : oldEntry.getTitle());

                    oldEntry.setContent(newJournalEntry.getContent() != null
                            && newJournalEntry.getContent().equals("") ? newJournalEntry.getContent() : oldEntry.getContent()
                    );
                    return journalRepository.save(oldEntry);

                }).orElseThrow(() -> new EntityNotFoundException("Not found"));


    }
}
