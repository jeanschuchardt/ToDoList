package com.jb.ItemService.service;

import com.jb.ItemService.entity.Role;
import com.jb.ItemService.entity.User;
import com.jb.ItemService.exception.ApiRequestException;
import com.jb.ItemService.record.SimpleUserResponseDTO;
import com.jb.ItemService.record.UserRequestDTO;
import com.jb.ItemService.record.UserResponseDTO;
import com.jb.ItemService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    private final JwtService jwtService;
    
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setRole(Role.USER);
        
        User save = saveUser(user);
        
        var jwkToken = jwtService.generateToken(user);
        
        
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), jwkToken);
        
    }
    
    public User updateUser(int userid, UserRequestDTO userRequest) {
        User user = getUser(userid);
        
        
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        if (Objects.nonNull(userRequest.password()) && userRequest.password().equals("")) {
            user.setPassword(passwordEncoder.encode(userRequest.password()));
        }
        
        return saveUser(user);
    }
    
    private User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ApiRequestException("Error to update user  " + user.getEmail() + ".", HttpStatus.BAD_REQUEST);
        }
    }
    
    public User getUser(int userid) {
        User user = userRepository.findByIdAndIsArchived(userid, false)
                                  .orElseThrow(() -> new ApiRequestException("", HttpStatus.NOT_FOUND));
        return user;
    }
    
    
    public SimpleUserResponseDTO mapResponse(User user) {
        SimpleUserResponseDTO simpleUserResponseDTO = new SimpleUserResponseDTO(user.getId(), user.getName(),
                user.getEmail());
        return simpleUserResponseDTO;
    }
}
