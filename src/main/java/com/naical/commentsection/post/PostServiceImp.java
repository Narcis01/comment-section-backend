package com.naical.commentsection.post;

import com.naical.commentsection.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final KafkaProducerService kafkaProducerService;
    @Override
    public List<PostDTO> getAll() {
        List<PostDTO> posts = postRepository.findAll().stream().map(postMapper::postToPostDTO).toList();
        kafkaProducerService.sendPostListEvent(posts,"Posts found: ");
        return posts;
    }


    @Override
    public Post save(Post post) {
        Post postSaved = postRepository.save(post);
        kafkaProducerService.sendPostEvent(postMapper.postToPostDTO(postSaved), "Post with id " + postSaved.getId() + " saved");
        return postSaved;
    }

    @Override
    public void delete(int id) {
        Post post = postRepository.findById(id).orElseThrow();
        kafkaProducerService.sendPostEvent(postMapper.postToPostDTO(post),"Post with id " + post.getId() + " was deleted");
        postRepository.deleteById(id);
    }

    @Override
    public Post getById(int id) {
        Post post = postRepository.findById(id).orElseThrow();
        kafkaProducerService.sendPostEvent(postMapper.postToPostDTO(post), "Post found by id " + post.getId());
        return post;
    }
}
