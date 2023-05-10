package com.jb.ItemService.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class ListControllerTest {
    
    @Autowired
    private ListController listController;
    
    @Test
    public void contextLoads() throws Exception{
        assertThat(listController).isNotNull();
        
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