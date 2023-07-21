package com.naical.commentsection.controller;

import com.naical.commentsection.post.Post;
import com.naical.commentsection.post.PostServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostServiceImp postServiceImp;

    @GetMapping("/posts")
    public List<Post> getAll()
    {
        return postServiceImp.getAll();
    }
}
