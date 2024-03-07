package com.naical.commentsection.security.user;

import com.naical.commentsection.kafka.KafkaProducerService;
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
    private final KafkaProducerService kafkaProducerService;
    private final UserMapper userMapper;
    @Override
    public List<User> findAllByRole(Role role) {
        List<User> users = userRepository.findAllByRole(role);
        kafkaProducerService.sendUserListEvent(users.stream().map(userMapper::userToUserDTO).toList(), "Users with role " + role + " found:");
        return userRepository.findAllByRole(role);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        tokenRepository.findAllByUser(user).forEach(tokenRepository::delete);
        userRepository.deleteById(id);
        kafkaProducerService.sendUserEvent(userMapper.userToUserDTO(user),"User deleted:");

    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        kafkaProducerService.sendUserEvent(userMapper.userToUserDTO(user), "User found with id " + id + " :");
        return user;
    }

    @Override
    public User save(User user) {
        User userSaved = userRepository.save(user);
        kafkaProducerService.sendUserEvent(userMapper.userToUserDTO(userSaved), "User saved:");
        return userSaved;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        kafkaProducerService.sendUserEvent(userMapper.userToUserDTO(user), "User found with email " + email + " :");
        return user;
    }

    @Override
    public User findByName(String firstName, String lastName) {
        User user = userRepository.findByFirstNameAndLastName(firstName,lastName);
        kafkaProducerService.sendUserEvent(userMapper.userToUserDTO(user), "User found by firstname " + firstName + " and lastname " + lastName + " :");
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        kafkaProducerService.sendUserListEvent(users.stream().map(userMapper::userToUserDTO).toList(), "Users found:");
        return userRepository.findAll();
    }
}
