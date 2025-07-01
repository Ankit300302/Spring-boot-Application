package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {


    @Autowired
    private UserService userService;

    @GetMapping("health-check")
    public String heathCheck(){
        return "Ok";
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Users users) {
        userService.saveNewUser(users);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
