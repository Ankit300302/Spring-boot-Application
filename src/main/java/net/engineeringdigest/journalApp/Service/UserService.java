package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEconder = new BCryptPasswordEncoder();


    public void saveEntry(Users users){

        userRepository.save(users);
    }
    public List<Users> getall(){
        return userRepository.findAll();
    }

    public Optional<Users> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public Users findByUsername(String username){
        return userRepository.findByUsername(username);
                //.orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
    public void saveNewUser(Users users){
        users.setPassword(passwordEconder.encode(users.getPassword()));
        users.setRoles(new HashSet<>(Arrays.asList("USER")));  // or Set.of("USER")
        userRepository.save(users);
    }

    public void saveAdmin (Users users){
        users.setPassword(passwordEconder.encode(users.getPassword()));
        users.setRoles(new HashSet<>(Arrays.asList("USER","ADMIN")));  // or Set.of("USER")
        userRepository.save(users);
    }







}
