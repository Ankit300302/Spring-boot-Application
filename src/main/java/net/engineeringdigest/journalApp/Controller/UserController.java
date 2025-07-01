package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.Service.WeatherService;
import net.engineeringdigest.journalApp.apiRequest.WeatherResponse;
import net.engineeringdigest.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getall();
    }





    // Update user
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users users) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Users userInDB = userService.findByUsername(users.getUsername());
        if (userInDB != null) {
            userInDB.setUsername(users.getUsername());
            userInDB.setPassword(users.getPassword());
            userService.saveNewUser(userInDB);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/greet")
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            WeatherResponse.Current current = weatherResponse.getCurrent();
            greeting = String.format(", Weather: %s, Temperature: %d°C",
                    current.getWeather_descriptions().get(0),
                    current.getTemperature());
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }

}
