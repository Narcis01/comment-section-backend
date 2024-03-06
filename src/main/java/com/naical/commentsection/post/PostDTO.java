package com.naical.commentsection.post;

import com.naical.commentsection.comment.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
    private int id;
    private String content;
    private Long userId;
    private Set<CommentDTO> commentDTOS;
}
