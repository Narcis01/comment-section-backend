package com.naical.commentsection.security.user;

import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.comment.CommentDTO;
import com.naical.commentsection.comment.CommentMapper;
import com.naical.commentsection.post.Post;
import com.naical.commentsection.post.PostDTO;
import com.naical.commentsection.post.PostMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(target = "post", expression = "java(mapPosts(user.getPost()))"),
            @Mapping(target = "comment", expression = "java(mapComments(user.getComment()))")

    })
    UserDTO userToUserDTO(User user);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName")
    })
    User userDtoToUser(UserDTO user);

    default Set<CommentDTO> mapComments(Set<Comment> comments) {
        if (comments == null) {
            return Collections.emptySet();
        }
        CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);
        return comments.stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toSet());
    }

    default Set<PostDTO> mapPosts(Set<Post> posts) {
        if (posts == null) {
            return Collections.emptySet();
        }
        PostMapper postMapper = Mappers.getMapper(PostMapper.class);
        return posts.stream()
                .map(postMapper::postToPostDTO)
                .collect(Collectors.toSet());
    }
}
