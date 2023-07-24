package com.naical.commentsection.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.naical.commentsection.post.Post;
import com.naical.commentsection.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public CommentDTO toDTO(){
        return CommentDTO.builder()
                .id(this.id)
                .content(this.content)
                .postId(this.post.getId())
                .userFistName(this.user.getFirstName())
                .userLastName(this.user.getLastName())
                .userImageUrl(this.user.getImageUrl())
                .build();
    }

}
