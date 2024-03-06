package com.naical.commentsection.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.naical.commentsection.post.Post;
import com.naical.commentsection.security.user.User;
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
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;



}
