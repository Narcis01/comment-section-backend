package com.naical.commentsection.controller;

import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.comment.CommentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@Component
public class CommentController {

    private final CommentServiceImp commentServiceImpl;


    @GetMapping("/comments")
    public List<Comment> getAll(){
        return commentServiceImpl.getAll();
    }

}
