package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName(){
        assertNotNull(userRepository.findByUsername("user1"));
    }

}
