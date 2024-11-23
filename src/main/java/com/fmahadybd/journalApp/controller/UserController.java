package com.fmahadybd.journalApp.controller;

import com.fmahadybd.journalApp.entity.UserEntry;
import com.fmahadybd.journalApp.service.UserService;
import org.apache.coyote.Response;
import org.hibernate.dialect.MyISAMStorageEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<UserEntry> users = userService.getAllUser();
        return users.isEmpty()
                ? new ResponseEntity<>(NOT_FOUND)
                : new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody UserEntry userEntry){
        try{
            UserEntry savedUserEntry = userService.savedUserEntry(userEntry);
            return new ResponseEntity<>(savedUserEntry,CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }
/**
 * If I used the optional in the repository then i can use same
 * optional in the service then we can use the map in the controller
 * and also can use all of them
 * */
    @PutMapping("{usernameOfOld}")
    public ResponseEntity<?> updateUserEntryByUserName(@RequestBody UserEntry userEntry,
                                                       @PathVariable String usernameOfOld) {
        return userService.findByUsername(usernameOfOld)
                .map(existUser -> {
                    existUser.setUsername(
                            Optional.ofNullable(userEntry.getUsername())
                                    .filter(username -> !username.isBlank())
                                    .orElse(existUser.getUsername())
                    );
                    existUser.setPassword(
                            Optional.ofNullable(userEntry.getPassword())
                                    .filter(password -> !password.isBlank())
                                    .orElse(existUser.getPassword())
                    );
                    userService.savedUserEntry(existUser);
                    return new ResponseEntity<>(existUser, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username) {
    Optional<UserEntry> userEntryOfId = userService.findByUsername(username);
    return userEntryOfId.isEmpty()?
            new ResponseEntity<>(NOT_FOUND):
            new ResponseEntity<>(userEntryOfId,HttpStatus.OK);

    }
}
