package com.naical.commentsection.security.controller;

import com.naical.commentsection.security.role.Role;
import com.naical.commentsection.security.user.User;
import com.naical.commentsection.security.user.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserServiceImp userServiceImp;

    @GetMapping("/admins")
    public List<User> getAdmins(){
        return userServiceImp.findAllByRole(Role.ADMIN);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Long id){
        User user = userServiceImp.findById(id);
        userServiceImp.delete(id);
        return  ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user){
        return ResponseEntity.ok(userServiceImp.save(user));
    }
}
