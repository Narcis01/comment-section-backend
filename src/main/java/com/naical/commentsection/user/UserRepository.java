package com.naical.commentsection.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
public interface UserRepository extends JpaRepository<User, Integer> {
}
