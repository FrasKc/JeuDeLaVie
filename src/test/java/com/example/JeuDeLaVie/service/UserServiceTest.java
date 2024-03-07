package com.example.JeuDeLaVie.service;

import com.example.JeuDeLaVie.model.User;
import com.example.JeuDeLaVie.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertNotEquals("password", createdUser.getPassword()); // Assuming password is hashed
    }

    @Test
    void testAuthenticateUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword(userService.hashPassword("password"));
        when(userRepository.findByUsername("testUser")).thenReturn(user);
        assertTrue(userService.authenticateUser("testUser", "password"));
        assertFalse(userService.authenticateUser("testUser", "wrongPassword"));
    }
}
