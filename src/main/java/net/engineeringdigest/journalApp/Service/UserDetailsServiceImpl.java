package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username);
        if(users !=null){
           UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(users.getUsername())
                    .password(users.getPassword())
                   .roles(users.getRoles().toArray(new String[0]))
                   .build();
                    return userDetails;
        }
        throw new UsernameNotFoundException("User not found with username: "+username);
    }
}
