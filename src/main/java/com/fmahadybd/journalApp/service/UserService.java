package com.fmahadybd.journalApp.service;

import com.fmahadybd.journalApp.entity.UserEntry;
import com.fmahadybd.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntry> getAllUser(){
        return userRepository.findAll();
    }

    public UserEntry savedUserEntry
            (UserEntry userEntry){
         return userRepository.save(userEntry);
    }

    public void deleteUserEntry(Long id){
        userRepository.deleteById(id);
    }

    public Optional<UserEntry> getUserEntryById(Long id){
        return userRepository.findById(id);
    }

    public Optional<UserEntry> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
