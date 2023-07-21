package com.naical.commentsection.controller;

import com.naical.commentsection.user.User;
import com.naical.commentsection.user.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImp userServiceImp;

    @GetMapping("/users")
    public List<User> getAll(){
        return userServiceImp.getAll();
    }
}
