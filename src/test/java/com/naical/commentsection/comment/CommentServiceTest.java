package com.naical.commentsection.comment;

import com.naical.commentsection.post.Post;
import com.naical.commentsection.post.PostRepository;
import com.naical.commentsection.post.PostServiceImp;
import com.naical.commentsection.kafka.KafkaProducerService;
import com.naical.commentsection.security.user.User;
import com.naical.commentsection.security.user.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostServiceImp postServiceImp;
    @Mock
    private UserServiceImp userServiceImp;
    @Mock
    private KafkaProducerService kafkaProducerService;
    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentServiceImp commentServiceImp;

    @Test
    public void saveToCommentTest(){
        Post post = Post.builder().id(1).build();
        User user = User.builder().id(1L).build();
        Comment comment = Comment.builder().id(1).user(user).post(post).build();

        when(postServiceImp.getById(1)).thenReturn(post);
        when(userServiceImp.findById(1L)).thenReturn(user);
        when(commentRepository.save(any())).thenReturn(comment);

        CommentDTO commentDTO = CommentDTO.builder().id(1).postId(1).userId(1L).content("").build();
        Comment commentResult = commentServiceImp.saveToComment(commentDTO);

        assertThat(commentResult).isEqualTo(comment);

    }
}
