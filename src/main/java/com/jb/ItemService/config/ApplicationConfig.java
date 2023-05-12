package com.jb.ItemService.config;

import com.jb.ItemService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    
    private final UserRepository userRepository;
    
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmailAndIsArchived(username, false)
                                         .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
}
