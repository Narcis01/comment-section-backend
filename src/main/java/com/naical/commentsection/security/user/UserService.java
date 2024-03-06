package com.naical.commentsection.security.user;

import com.naical.commentsection.security.role.Role;

import java.util.List;

public interface UserService {
    List<User> findAllByRole(Role role);
    void delete(Long id);
    User findById(Long id);
    User save(User user);
    User findByEmail(String email);
    User findByName(String firstName, String lastName);
    List<User> findAll();
}
