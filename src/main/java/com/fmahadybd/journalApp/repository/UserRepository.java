package com.fmahadybd.journalApp.repository;

import com.fmahadybd.journalApp.entity.UserEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for JournalEntry entity.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntry, Long> {
    Optional<UserEntry> findByUsername(String username);
}
