package com.jb.ItemService.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ItemListController {
    
    @GetMapping("/api/v1/lists/{id}/items")
    public void get(@PathVariable int id){
    
    }
    
    @PostMapping("/api/v1/lists/{id}/items")
    public void post(@PathVariable int id, @RequestBody String name){
    
    }
    
    @PutMapping("/api/v1/lists/{listId}/items/{id}")
    public void post(@PathVariable int listId, @PathVariable int id ,@RequestBody String name){
    
    }
    
    @DeleteMapping("/api/v1/lists/{listId}/items/{id}")
    public void delete(@PathVariable int listId, @PathVariable int id){
    
    }
}
 /*
URI sugerida: /api/v{n}/lists/{ID}/items
Public: Não
Tipo: GET
Return Success: { "items" : { OBJECT1, OBJECT2 } }
Return Fail: { "message" : STRING }

URI sugerida: /api/v{n}/lists/{ID}/items
Public: Não
Tipo: POST
Request: { "title": STRING, "description": STRING, “user_id”: INTEGER }
Return Success: { "item" : OBJECT }
Return Fail: { "message" : STRING }

URI sugerida: /api/v{n}/lists/{ID}/items/{ID}
Public: Não
Tipo: PUT
Request: { "title": STRING, "description": STRING, “user_id”: INTEGER }
Return Success: { "item" : OBJECT }
Return Fail: { "message" : STRING }


URI sugerida: /api/v{n}/lists/{ID}/items/{ID}
Public: Não
Tipo: DELETE
Request: { "id": INTEGER }
Return Fail: { "message" : STRING }

*/