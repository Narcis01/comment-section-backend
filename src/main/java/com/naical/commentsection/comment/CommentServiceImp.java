package com.naical.commentsection.comment;

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
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }

    public Comment save(Comment comment){
        return commentRepository.save(comment);
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
        commentRepository.save(comment);

        return comment;
    }



}
