package com.fmahadybd.journalApp.controller;

import com.fmahadybd.journalApp.entity.JournalEntry;
import com.fmahadybd.journalApp.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

   @Autowired
   private JournalService journalService;

    @GetMapping("/")
    public List<JournalEntry> getAll(){
        return journalService.getAll();
    }
    @PostMapping
    public boolean savedNew(@RequestBody JournalEntry newEntry){
       return journalService.addNew(newEntry);

    }


    @GetMapping("/id/{myId}")
    public Optional<JournalEntry> getById(@PathVariable Long myId){
        return journalService.getById(myId);

    }

    @PostMapping("/add-new")
    public Boolean addNew(JournalEntry journalEntry){

        return journalService.addNew(journalEntry);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateById(@RequestBody JournalEntry journalEntry
                                                    ,@PathVariable Long id){
        return journalService.updateById(id,journalEntry);

    }
    @DeleteMapping("/id/{id}")
    public String deleteEntry(@PathVariable Long id){
        return journalService.delete(id);
    }

}
