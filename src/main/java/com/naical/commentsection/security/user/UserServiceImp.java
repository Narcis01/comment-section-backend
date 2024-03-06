package com.naical.commentsection.security.user;

import com.naical.commentsection.security.role.Role;
import com.naical.commentsection.security.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp  implements UserService{

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        tokenRepository.findAllByUser(user).forEach(tokenRepository::delete);
        userRepository.deleteById(id);

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public User findByName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
