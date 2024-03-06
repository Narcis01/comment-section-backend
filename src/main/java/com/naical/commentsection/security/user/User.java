package com.naical.commentsection.security.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.post.Post;
import com.naical.commentsection.security.role.Role;
import com.naical.commentsection.security.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Token> tokens;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Post> post;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Comment> comment;

    public void addComment(Comment comment){
        if(this.comment == null){
            this.comment = new HashSet<>();
        }
        comment.setUser(this);
        this.comment.add(comment);
    }

    public void addPost(Post post){
        if(this.post == null){
            this.post = new HashSet<>();
        }
        post.setUser(this);
        this.post.add(post);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
