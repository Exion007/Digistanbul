package com.example._proj;

import com.mongodb.DuplicateKeyException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public List<User> fetchAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/username/{username}")
    public Optional<User> fetchSpecificUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/email/{email}")
    public Optional<User> fetchSpecificEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/delete/{username}")
    public Optional<User> deleteUsername (@PathVariable String username) {
        return userService.deleteUsername(username);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email address is already in use");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {
        if (userService.loginUser(username, password)) {
            return ResponseEntity.ok().body("Login successful for user: " + username);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, String> updateRequest) {
        try {
            String email = updateRequest.get("email");
            String username = updateRequest.get("username");
            String oldPassword = updateRequest.get("oldPassword");
            String newPassword = updateRequest.get("newPassword");

            Optional<User> existingUserOptional = userService.getUserByEmail(email);

            if (existingUserOptional.isPresent()) {
                User existingUser = existingUserOptional.get();

                if (existingUser.getUsername().equals(username) &&
                        existingUser.getPassword().equals(oldPassword)) {

                    existingUser.setPassword(newPassword);

                    User updatedUserResult = userService.updateUser(existingUser);

                    return ResponseEntity.ok().body(updatedUserResult);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or old password");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred");
        }
    }
}