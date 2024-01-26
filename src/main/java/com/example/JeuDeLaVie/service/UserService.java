package com.example.JeuDeLaVie.service;

import com.example.JeuDeLaVie.model.User;
import com.example.JeuDeLaVie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return user;
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            String hashedPassword = hashPassword(password);
            return hashedPassword.equals(user.getPassword());
        }
        return false;
    }

    private String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
