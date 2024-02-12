package com.example._proj;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> deleteUsername(String username) {
        return userRepository.deleteUserByUsername(username);
    }

    public User registerUser(User user) {
        user.setPassword(user.getPassword());

        return userRepository.save(user);
    }

    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return true; // Successful login
        }

        return false; // Invalid credentials
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public boolean doesUsernameExist(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

    public User updateProfilePicture(String username, String imagePath) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setImagePath(imagePath);
            return userRepository.save(user);
        }
        return null;
    }

    public User updateProfileImage(String username, String imagePath) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setImagePath(imagePath);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

}