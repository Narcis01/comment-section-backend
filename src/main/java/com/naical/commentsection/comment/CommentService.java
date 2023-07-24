package com.naical.commentsection.comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    void deleteById(int id);
    Comment save(Comment comment);
}
