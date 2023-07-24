package com.naical.commentsection.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.post.Post;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Post> post;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Comment> comment;

    public static UserDTO toDTO(User user){ return new UserDTO(user);}
    public void addPost(Post post){
        if(this.post == null){
            this.post = new HashSet<>();
        }
        post.setUser(this);
        this.post.add(post);
    }

    public void addComment(Comment comment){
        if(this.comment == null){
            this.comment = new HashSet<>();
        }
        comment.setUser(this);
        this.comment.add(comment);
    }
}
