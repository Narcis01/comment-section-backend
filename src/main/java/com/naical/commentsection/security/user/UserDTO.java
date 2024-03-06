package com.naical.commentsection.security.user;

import com.naical.commentsection.comment.CommentDTO;
import com.naical.commentsection.post.PostDTO;
import com.naical.commentsection.security.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Set<PostDTO> post;
    private Set<CommentDTO> comment;


}
