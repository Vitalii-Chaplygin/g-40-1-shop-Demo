package de.ait_tr.g_40_1_shop.service;

import de.ait_tr.g_40_1_shop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                ()->new  UsernameNotFoundException(String.format("USER %s not found",username))
        );


//Альтернативный код

//        User user = userRepository.findByUsername(username);
//        if (user==null){
//            throw new  UsernameNotFoundException(String.format("USER %s not found",username));
//        }
//return user;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
