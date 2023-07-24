package com.naical.commentsection.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostDTO> getAll() {
        return postRepository.findAll().stream().map(Post::toDTO).toList();
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post getById(int id) {
        return postRepository.findById(id).orElseThrow();
    }
}
