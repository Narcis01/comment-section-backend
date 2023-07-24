package com.naical.commentsection.controller;

import com.naical.commentsection.comment.CommentServiceImp;
import com.naical.commentsection.post.Post;
import com.naical.commentsection.post.PostDTO;
import com.naical.commentsection.post.PostServiceImp;
import com.naical.commentsection.user.User;
import com.naical.commentsection.user.UserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
@Slf4j
public class PostController {

    private final PostServiceImp postServiceImp;
    private final UserServiceImp userServiceImp;
    @GetMapping("/posts")
    public List<PostDTO> getAll() {
        return postServiceImp.getAll();
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<Post> save(@RequestBody Post post, @PathVariable("id") int id) {
        User user = userServiceImp.getUserById(id);
        user.addPost(post);
        userServiceImp.save(user);
        postServiceImp.save(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Post> delete(@PathVariable("id") int id) {
        Post post = postServiceImp.getById(id);
        postServiceImp.delete(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
