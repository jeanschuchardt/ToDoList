package com.jb.ItemService.controller;

import com.jb.ItemService.record.Login;
import com.jb.ItemService.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    
    private  final TokenService tokenService;
    
    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    
    @PostMapping("/api/v1/authenticate")
    public String authenticate(Authentication authentication){
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String jwt = tokenService.generateToken(authentication);
        LOG.debug("Token granted {}", jwt);
        return jwt;
        
    }
    
    
    @PostMapping("/api/v1/sso")
    public String sso(Authentication authentication){
        LOG.debug("Token requested for user: '{}'", authentication.getName());
        String jwt = tokenService.generateToken(authentication);
        LOG.debug("Token granted {}", jwt);
        return jwt;
        
    }
    
    @PostMapping("/login")
    public Login  login(@RequestBody Login login){
        return login;
    }
    
    @GetMapping("/login/principal")
    public String  getPrincipal(Principal principal){
        return "Hi " + principal.getName();
    }
}
