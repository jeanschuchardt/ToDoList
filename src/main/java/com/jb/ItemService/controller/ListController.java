package com.jb.ItemService.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ListController {
    
    @GetMapping("/api/v1/lists/{id}")
    public int get(@PathVariable int id) {
        return id;
    }
    
    @PostMapping("/api/v1/lists")
    public void post(@RequestBody String name) {
    
    }
    
    @DeleteMapping("/api/v1/lists/{id}")
    public void delete(@PathVariable int id) {
    
    }
}
 /*
URI sugerida: /api/v{n}/lists/{ID}
Public: Sim
Tipo: GET
Return Success: { "list" : OBJECT,  “user_id”: INTEGER }
Return Fail: { "message" : STRING }

URI sugerida: /api/v{n}/lists
Public: Sim
Tipo: POST
Request: { "title": STRING }
Return Success: { "list" : { "title" : STRING } }
Return Fail: { "message" : STRING }


URI sugerida: /api/v{n}/lists/{ID}
Public: Não
Tipo: DELETE
Request: { "id": INTEGER }
Return Fail: { "message" : STRING }

*/