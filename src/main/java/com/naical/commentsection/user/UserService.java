package com.naical.commentsection.user;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserById(int id);
    User save(User user);
}
