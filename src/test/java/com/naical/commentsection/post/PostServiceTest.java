package com.naical.commentsection.post;

import com.naical.commentsection.kafka.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostMapper postMapper;
    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private PostServiceImp postServiceImp;

    @Test
    public void saveTest(){
        Post post = Post.builder().id(1).build();
        when(postRepository.save(post)).thenReturn(post);
        Post postResult = postServiceImp.save(post);
        verify(postRepository).save(post);
        assertThat(postResult).isEqualTo(post);
    }

    @Test
    public void getByIdTest(){
        Post post = Post.builder().id(1).build();
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        Post postResult = postServiceImp.getById(1);
        verify(postRepository).findById(1);
        assertThat(postResult).isEqualTo(post);
    }
}
