package com.naical.commentsection.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "post.id", target = "postId"),
            @Mapping(source = "user.id", target = "userId")
    })
    CommentDTO commentToCommentDTO(Comment comment);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "content", target = "content")
    })
    Comment commentDtoToComment(CommentDTO commentDTO);
}
