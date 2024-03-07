package com.naical.commentsection.comment;

import com.naical.commentsection.kafka.KafkaProducerService;
import com.naical.commentsection.post.Post;
import com.naical.commentsection.post.PostRepository;
import com.naical.commentsection.post.PostServiceImp;
import com.naical.commentsection.security.user.User;
import com.naical.commentsection.security.user.UserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImp implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final PostServiceImp postServiceImp;
    private final UserServiceImp userServiceImp;
    private final KafkaProducerService kafkaProducerService;
    private final CommentMapper commentMapper;
    @Override
    public List<Comment> getAll() {
        List<Comment> comments = commentRepository.findAll();
        kafkaProducerService.sendCommentListEvent(comments.stream().map(commentMapper::commentToCommentDTO).toList(), "Comment found: ");
        return comments;
    }

    @Override
    public void deleteById(int id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        kafkaProducerService.sendCommentEvent(commentMapper.commentToCommentDTO(comment), "Comment deleted: ");
        commentRepository.deleteById(id);
    }

    @Override
    public Comment saveToComment(CommentDTO commentDTO) {
        Post post = postServiceImp.getById(commentDTO.getPostId());
        log.info("Post found: " + post);
        User user = userServiceImp.findById(commentDTO.getUserId());
        Comment comment = Comment.builder().content(commentDTO.getContent()).build();
        post.addComment(comment);
        user.addComment(comment);

        postRepository.save(post);
        userServiceImp.save(user);
        Comment commentSaved = commentRepository.save(comment);
        kafkaProducerService.sendCommentEvent(commentMapper.commentToCommentDTO(commentSaved), "Comment saved: ");
        return commentSaved;
    }

    @Override
    public Comment getById(int id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        kafkaProducerService.sendCommentEvent(commentMapper.commentToCommentDTO(comment), "Comment found: ");
        return comment;
    }


}
