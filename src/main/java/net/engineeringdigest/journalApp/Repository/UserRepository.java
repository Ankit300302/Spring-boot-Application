package net.engineeringdigest.journalApp.Repository;


import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<Users, ObjectId> {



    Users findByUsername(String username);

    void deleteByUsername(String name);
}

// journalEntry ==> JournalEntryRepo ==> JournalEntryService ==> JournalEntryControllerV2