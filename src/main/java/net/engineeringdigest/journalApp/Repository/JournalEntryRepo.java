package net.engineeringdigest.journalApp.Repository;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
}

// journalEntry ==> JournalEntryRepo ==> JournalEntryService ==> JournalEntryControllerV2