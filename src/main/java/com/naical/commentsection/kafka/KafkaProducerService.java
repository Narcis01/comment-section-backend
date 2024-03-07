package com.naical.commentsection.kafka;

import com.naical.commentsection.comment.CommentDTO;
import com.naical.commentsection.post.PostDTO;
import com.naical.commentsection.security.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String USER_TOPIC = "user";
    public static final String COMMENT_TOPIC = "comment";
    public static final String POST_TOPIC = "post";

    public void sendUserEvent(UserDTO userDTO, String message){
        kafkaTemplate.send(USER_TOPIC, message + " " + userDTO);
    }
    public void sendUserListEvent(List<UserDTO> users, String message){
        kafkaTemplate.send(USER_TOPIC, message + " " + users);
    }

    public void sendAuthEvent(String message){
        kafkaTemplate.send(USER_TOPIC, message);
    }

    public void sendPostEvent(PostDTO postDTO , String message){
        kafkaTemplate.send(POST_TOPIC, message + " " + postDTO);
    }

    public void sendPostListEvent(List<PostDTO> posts,String message){
        kafkaTemplate.send(POST_TOPIC, message + " " + posts);
    }

    public void sendCommentEvent(CommentDTO commentDTO , String message){
        kafkaTemplate.send(COMMENT_TOPIC, message + " " + commentDTO);
    }

    public void sendCommentListEvent(List<CommentDTO> commentDTOS, String message){
        kafkaTemplate.send(COMMENT_TOPIC, message + " " + commentDTOS);
    }
}
