package com.example.driveintheater.service;

import com.example.driveintheater.model.User;
import com.example.driveintheater.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(String username, String rawPassword, String email) {
        if (userRepository.existsByUsername(username)) {
            return "username_exists";
        }
        if (userRepository.existsByEmail(email)) {
            return "email_exists";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("USER");
        user.setEmail(email);

        userRepository.save(user);

        return "success";
    }
}
