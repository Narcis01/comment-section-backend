package com.naical.commentsection.user;

import com.naical.commentsection.kafka.KafkaProducerService;
import com.naical.commentsection.security.role.Role;
import com.naical.commentsection.security.token.TokenRepository;
import com.naical.commentsection.security.user.User;
import com.naical.commentsection.security.user.UserMapper;
import com.naical.commentsection.security.user.UserRepository;
import com.naical.commentsection.security.user.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private KafkaProducerService kafkaProducerService;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImp userServiceImp;
    @Test
    public void findByIdTest(){
        User userToFind = User.builder()
                .email("test@mail.com")
                .id(1L)
                .build();
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(userToFind));
        User userFound = userServiceImp.findById(1L);
        verify(userRepository).findById(any());
        assertThat(userFound).isEqualTo(userToFind);
    }

    @Test
    public void findAllByRoleTest(){
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAllByRole(Role.ADMIN)).thenReturn(users);
        List<User> usersResult = userServiceImp.findAllByRole(Role.ADMIN);
        verify(userRepository).findAllByRole(Role.ADMIN);
        assertThat(usersResult).isEqualTo(users);
    }

    @Test
    public void saveTest(){
        User user = User.builder().id(1L).email("test@mail.com").build();
        when(userRepository.save(user)).thenReturn(user);
        User userResult = userServiceImp.save(user);
        verify(userRepository).save(user);
        assertThat(userResult).isEqualTo(user);
    }
}
