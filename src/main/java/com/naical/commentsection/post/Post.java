package com.naical.commentsection.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.security.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Comment> comment;

    public void addComment(Comment comment){
        if(this.comment == null){
            this.comment = new HashSet<>();
        }
        this.comment.add(comment);
        comment.setPost(this);
    }

}
