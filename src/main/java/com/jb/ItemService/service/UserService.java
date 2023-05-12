package com.jb.ItemService.service;

import com.jb.ItemService.entity.Role;
import com.jb.ItemService.entity.User;
import com.jb.ItemService.exception.ServiceException;
import com.jb.ItemService.record.UserRequestDTO;
import com.jb.ItemService.record.UserResponse;
import com.jb.ItemService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    private final JwtService jwtService;
    
    public UserResponse createUser(UserRequestDTO userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setRole(Role.USER);
        
        User save = userRepository.save(user);
        
        var jwkToken = jwtService.generateToken(user);
        
        
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), jwkToken);
        
    }
    
    public User updateUser(int userid, UserRequestDTO userRequest) {
        User user = getUser(userid);
        
        
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        
        return userRepository.save(user);
    }
    
    public User getUser(int userid) {
        User user = userRepository.findByIdAndIsArchived(userid, false)
                                  .orElseThrow(() -> new ServiceException("", HttpStatus.NOT_FOUND));
        return user;
    }
    
    
}
