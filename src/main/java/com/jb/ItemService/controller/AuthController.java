package com.jb.ItemService.controller;

import com.jb.ItemService.record.Login;
import com.jb.ItemService.repository.UserRepository;
import com.jb.ItemService.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {
    
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    
    private final AuthenticationManager authenticationManager;
    
    private final UserRepository userRepository;
    private final JwtService jwtService;
    
    
    @PostMapping("/api/v1/authenticate")
    public String authenticate(Login request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.user(),
                        request.password()
                )
        );
        
        var user = userRepository.findByEmail(request.user()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return jwtToken;
        
    }
    
    
    @PostMapping("/api/v1/sso")
    public String sso(Authentication authentication) {
        
        return null;
        
    }
    
}
