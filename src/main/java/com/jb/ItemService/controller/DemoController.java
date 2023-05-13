package com.jb.ItemService.controller;

import com.jb.ItemService.record.AuthenticationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping
    public String authenticate(AuthenticationRequestDTO request) {
        return "Hello.";
        
    }
    
}
