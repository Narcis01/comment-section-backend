package com.naical.commentsection.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImp userServiceImp;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/users")
    public List<UserDTO> getUsers(){
        return userServiceImp.findAll().stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
    }
    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userMapper.userToUserDTO(userServiceImp.save(user)));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id){
        UserDTO userDTO = userMapper.userToUserDTO(userServiceImp.findById(id));
        userServiceImp.delete(id);
        return ResponseEntity.ok("Deleted:" + userDTO);
    }
    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody User user){
        User userDB = userServiceImp.findById(user.getId());
        if(user.getEmail() != null){
            userDB.setEmail(user.getEmail());
        }
        if(user.getRole() != null){
            userDB.setRole(user.getRole());
        }
        if(user.getFirstName() != null){
            userDB.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null){
            userDB.setLastName(user.getLastName());
        }
        if(user.getPassword() != null){
            userDB.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        UserDTO userDTOSaved = userMapper.userToUserDTO(userServiceImp.save(userDB));
        return ResponseEntity.ok(userDTOSaved);
    }


}
