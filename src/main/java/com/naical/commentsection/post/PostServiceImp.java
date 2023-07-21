package com.naical.commentsection.post;

import com.naical.commentsection.user.User;
import com.naical.commentsection.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostServiceImp implements PostService{

    private final PostRepository postRepository;

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }
}
