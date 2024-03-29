package com.naical.commentsection.post;


import com.naical.commentsection.security.user.User;
import com.naical.commentsection.security.user.UserServiceImp;
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
    private final PostMapper postMapper;
    @GetMapping("/posts")
    public List<PostDTO> getAll() {
        return postServiceImp.getAll();
    }

    @PostMapping("/save")
    public ResponseEntity<PostDTO> save(@RequestBody PostDTO postDTO) {
        User user = userServiceImp.findById(postDTO.getUserId());
        Post post = Post.builder().content(postDTO.getContent()).build();
        user.addPost(post);
        userServiceImp.save(user);
        postServiceImp.save(post);
        return new ResponseEntity<>(postMapper.postToPostDTO(postServiceImp.save(post)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Post> delete(@PathVariable("id") int id) {
        Post post = postServiceImp.getById(id);
        postServiceImp.delete(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


}
