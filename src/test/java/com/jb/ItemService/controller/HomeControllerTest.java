package com.jb.ItemService.controller;

import com.jb.ItemService.config.SecurityConfig;
import com.jb.ItemService.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({AuthController.class})
@Import({SecurityConfig.class, TokenService.class})
class HomeControllerTest {
    
    @Autowired
    MockMvc mvc;
    
    @Test
    void rootWhenUnauthenticatedThen401() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }
    
    @Test
    void rootWhenAuthenticatedThenSaysHelloUser() throws Exception {
        MvcResult result = this.mvc.perform(post("/token")
                                       .with(httpBasic("demo", "123123")))
                                   .andExpect(status().isOk())
                                   .andReturn();
        
        String token = result.getResponse().getContentAsString();
        
        this.mvc.perform(get("/login/principal")
                    .header("Authorization", "Bearer " + token))
                .andExpect(content().string("Hi demo"));
    }
    
    @Test
    @WithMockUser
    public void rootWithMockUserStatusIsOK() throws Exception {
        this.mvc.perform(get("/login/principal")).andExpect(status().isOk());
    }
    
}
