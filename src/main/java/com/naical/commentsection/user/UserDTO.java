package com.naical.commentsection.user;

import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.comment.CommentDTO;
import com.naical.commentsection.post.Post;
import com.naical.commentsection.post.PostDTO;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private Set<PostDTO> post;
    private Set<CommentDTO> comment;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.imageUrl = user.getImageUrl();
        this.post = user.getPost().stream().map(Post::toDTO).collect(Collectors.toSet());
        this.comment = user.getComment().stream().map(Comment::toDTO).collect(Collectors.toSet());
    }
}
