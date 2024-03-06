package com.naical.commentsection.security.user;

import com.naical.commentsection.security.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);
    List<User> findAllByRole(Role role);
    User findByFirstNameAndLastName(String firstName, String lastName);

}
