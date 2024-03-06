package com.naical.commentsection.post;

import java.util.List;

public interface PostService {
    List<PostDTO> getAll();
    Post save(Post post);
    void delete(int id);
    Post getById(int id);
}
