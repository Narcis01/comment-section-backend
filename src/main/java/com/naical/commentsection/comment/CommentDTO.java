package com.naical.commentsection.comment;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private int id;
    private String content;
    private int postId;
    private String userFistName;
    private String userLastName;
    private String userImageUrl;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.postId = comment.getPost().getId();
        this.userFistName = comment.getUser().getFirstName();
        this.userLastName = comment.getUser().getLastName();
        this.userImageUrl = comment.getUser().getImageUrl();
    }
}
