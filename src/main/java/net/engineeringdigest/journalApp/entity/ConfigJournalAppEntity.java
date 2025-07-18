package net.engineeringdigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "journal_entries")
@Data
@NoArgsConstructor

public class ConfigJournalAppEntity {

    private String key;
    private String value;



}
