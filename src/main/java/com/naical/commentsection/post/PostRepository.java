package com.naical.commentsection.post;

import com.naical.commentsection.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("")
public interface PostRepository extends JpaRepository<Post, Integer> {
    
}
