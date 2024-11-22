package com.fmahadybd.journalApp.repository;

import com.fmahadybd.journalApp.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JournalRepository extends JpaRepository<JournalEntry,Long>
{
    Optional<JournalEntry> findById(long id);
}
