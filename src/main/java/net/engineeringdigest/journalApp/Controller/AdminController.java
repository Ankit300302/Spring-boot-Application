package net.engineeringdigest.journalApp.Controller;


import net.engineeringdigest.journalApp.APPcache.AppCache;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")




public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> all = userService.getall();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody Users users) {
        userService.saveAdmin(users);
    }

    @GetMapping("Clear-cache")
    public void clearappcache() {
        appCache.init();

    }
}
