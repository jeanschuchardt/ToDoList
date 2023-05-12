package com.jb.ItemService.controller;

import com.jb.ItemService.record.Login;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class LoginController {

    @PostMapping("/login")
    public Login  login(@RequestBody Login login){
        return login;
    }
    
    @GetMapping("/login/principal")
    public String  getPrincipal(Principal principal){
        return "Hi " + principal.getName();
    }
}
