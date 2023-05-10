package com.jb.ItemService.controller;

import com.jb.ItemService.entity.TaskList;
import com.jb.ItemService.service.TaskListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(ListController.class)
class ListControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    
    @MockBean
    private TaskListService service;
    
    
    @Test
    public void contextLoads() throws Exception {
        when(service.test("Jean")).thenReturn("Hi user Jean");
        this.mockMvc
                .perform(get("/api/v1/test/{text}","Jean"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hi user Jean")));
        
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