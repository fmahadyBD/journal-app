package com.fmahadybd.journalApp.controller;

import com.fmahadybd.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long,JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean savedNew(@RequestBody JournalEntry newEntry){
        journalEntries.put(newEntry.getId(),newEntry);;
        return true;

    }
    @GetMapping("/id/{myId}")
    public JournalEntry getById(@PathVariable Long myId){
        return journalEntries.get(myId);

    }

    @PutMapping("/id/{id}")
    public JournalEntry updateById(@RequestBody JournalEntry journalEntry
                                                    ,@PathVariable Long id){
        return journalEntries.put(id,journalEntry);

    }
    @DeleteMapping("/id/{id}")
    public String deleteEntry(@PathVariable Long id){
        journalEntries.remove(id);
        return "delete sucessfuly";
    }

}
