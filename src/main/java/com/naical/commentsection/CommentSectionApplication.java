package com.naical.commentsection;

import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.comment.CommentRepository;
import com.naical.commentsection.post.Post;
import com.naical.commentsection.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class  CommentSectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentSectionApplication.class, args);
	}


}
