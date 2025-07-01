package net.engineeringdigest.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Document(collection = "Users_entries")
@NoArgsConstructor
@Data


public class Users {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String username;
    private String email;
    private boolean SentimentAnalysis;
    @NonNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();


    private Set<String> roles;

}
