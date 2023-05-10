package com.jb.ItemService.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ListControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void contextLoads() throws Exception{
        this.mockMvc.perform(get("/api/v1/lists/1")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("1")));
        
    }
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void get() {
//    }
//
//    @Test
//    void post() {
//    }
//
//    @Test
//    void delete() {
//    }
}