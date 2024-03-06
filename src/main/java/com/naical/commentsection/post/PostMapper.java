package com.naical.commentsection.post;

import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.comment.CommentDTO;
import com.naical.commentsection.comment.CommentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(target = "commentDTOS", expression = "java(mapComments(post.getComment()))")

    })
    PostDTO postToPostDTO(Post post);
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "content", target = "content")

    })
    Post postDtoToPost(PostDTO postDTO);
    default Set<CommentDTO> mapComments(Set<Comment> comments) {
        if (comments == null) {
            return Collections.emptySet();
        }
        CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);
        return comments.stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toSet());
    }
}
