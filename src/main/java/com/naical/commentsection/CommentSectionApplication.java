package com.naical.commentsection;

import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.comment.CommentRepository;
import com.naical.commentsection.post.Post;
import com.naical.commentsection.post.PostRepository;
import com.naical.commentsection.user.User;
import com.naical.commentsection.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class  CommentSectionApplication implements ApplicationRunner {

	private final PostRepository postRepository;
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CommentSectionApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user = User.builder().firstName("First Name").lastName("Last Name").imageUrl("assets/image/profile.png").build();
		Post post1 = Post.builder().content("Post 1 from run").build();
		Comment comment = Comment.builder().content("Comment 98435 from run").build();

		user.addPost(post1);
		user.addComment(comment);
		post1.addComment(comment);

		userRepository.save(user);
		postRepository.save(post1);
		commentRepository.save(comment);
		log.info("...Saved...");

		//postRepository.delete(post1);
		log.info("...Deleted...");


	}
}
