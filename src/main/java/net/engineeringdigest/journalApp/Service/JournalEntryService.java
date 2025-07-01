package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    @Transactional    // means the whole code should run inside the function if any part is failed roll back
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            Users users = userService.findByUsername(userName);
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            users.getJournalEntries().add(saved);
            userService.saveEntry(users);
        } catch (Exception e){
            logger.info("");
            throw  new RuntimeException("An error occurred while saving the entry. ",e);
        }
    }
    public void saveEntry(JournalEntry journalEntry){
       journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getall(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }


    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            Users users = userService.findByUsername(userName);
           removed = users.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveEntry(users);
                journalEntryRepo.deleteById(id);
            }


        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while the entry being deleted",e);

        }
        return removed;
    }

   // public List<JournalEntry> findByUserName



}
