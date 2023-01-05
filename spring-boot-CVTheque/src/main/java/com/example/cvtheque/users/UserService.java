package com.example.cvtheque.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoderConfig passwordEncoderConfig;

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new  User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
    public UserEntity loadUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user;
    }
}
