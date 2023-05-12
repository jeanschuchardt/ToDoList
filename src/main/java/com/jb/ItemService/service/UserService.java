package com.jb.ItemService.service;

import com.jb.ItemService.entity.User;
import com.jb.ItemService.exception.ServiceException;
import com.jb.ItemService.record.UserRequestDTO;
import com.jb.ItemService.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User createUser(UserRequestDTO userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        
        return userRepository.save(user);
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
