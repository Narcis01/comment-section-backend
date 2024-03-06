package com.naical.commentsection.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Override
    public List<PostDTO> getAll() {
        return postRepository.findAll().stream().map(postMapper::postToPostDTO).toList();
    }


    @Override
    public Post save(Post post) {
       return postRepository.save(post);
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
