package com.naical.commentsection.security.controller;

import com.naical.commentsection.security.role.Role;
import com.naical.commentsection.security.user.User;
import com.naical.commentsection.security.user.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImp userServiceImp;

    @GetMapping("/managers")
    public List<User> getManagers() {
        return userServiceImp.findAllByRole(Role.MANAGER);
    }

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        User userFromDb = userServiceImp.findByEmail(user.getEmail());

        if (user.getPassword() != null) {
            userFromDb.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getFirstName() != null) {
            userFromDb.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userFromDb.setLastName(user.getLastName());
        }
        if (user.getRole() != null) {
            userFromDb.setRole(user.getRole());
        }

        return ResponseEntity.ok(userServiceImp.save(userFromDb));
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return userServiceImp.findByName(firstName, lastName);
    }
}
