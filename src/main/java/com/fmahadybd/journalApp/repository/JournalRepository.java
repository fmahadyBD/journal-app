package com.fmahadybd.journalApp.repository;

import com.fmahadybd.journalApp.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for JournalEntry entity.
 */
@Repository
public interface JournalRepository extends JpaRepository<JournalEntry, Long> {
}
