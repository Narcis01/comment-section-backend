package com.naical.commentsection.controller;

import com.naical.commentsection.user.User;
import com.naical.commentsection.user.UserDTO;
import com.naical.commentsection.user.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImp userServiceImp;

    @GetMapping("/users")
    public List<UserDTO> getAll(){
        return userServiceImp.getAll().stream().map(User::toDTO).toList();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id){
        return userServiceImp.getUserById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user){
        userServiceImp.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
