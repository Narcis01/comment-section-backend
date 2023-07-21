package com.naical.commentsection.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService{

    private final CommentRepository commentRepository;


    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }
}
