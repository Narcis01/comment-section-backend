package com.naical.commentsection.post;

import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.comment.CommentDTO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int id;
    private String content;
    private int userId;
    private Set<CommentDTO> comments;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.userId = post.getUser().getId();
        this.comments = post.getComment().stream().map(Comment::toDTO).collect(Collectors.toSet());

    }
}
