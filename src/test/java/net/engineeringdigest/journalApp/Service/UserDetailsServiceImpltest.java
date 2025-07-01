package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.when;


public class UserDetailsServiceImpltest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        // ✅ Create your custom Users entity object
        Users mockUser = new Users();
        mockUser.setUsername("user1");
        mockUser.setPassword("hbdfui");
        mockUser.setRoles(new HashSet<>(Arrays.asList("USER")));

        when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(mockUser);

        UserDetails user = userDetailsService.loadUserByUsername("user1");

        Assertions.assertNotNull(user);
        Assertions.assertEquals("user1", user.getUsername());
    }
}
