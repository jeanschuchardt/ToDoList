package com.jb.ItemService.controller;

import com.jb.ItemService.entity.TaskItem;
import com.jb.ItemService.record.ItemListRequestDTO;
import com.jb.ItemService.record.ResponseDTO;
import com.jb.ItemService.record.ResponseListDTO;
import com.jb.ItemService.service.TaskItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemListController {
    
    public final TaskItemService taskItemService;
    
    public ItemListController(TaskItemService taskItemService) {
        this.taskItemService = taskItemService;
        
    }
    
    @GetMapping("/api/v1/lists/{id}/items")
    public ResponseListDTO<TaskItem> get(@PathVariable int id) {
        List<TaskItem> itemsByList = taskItemService.getItemsByList(id);
        return new ResponseListDTO<TaskItem>(itemsByList);
    }
    
    @PostMapping("/api/v1/lists/{id}/items")
    public ResponseDTO<TaskItem> post(@PathVariable int id, @RequestBody ItemListRequestDTO request) {
        TaskItem taskItem = taskItemService.create(id, request);
        return new ResponseDTO<TaskItem>(taskItem);
    }
    
    @PutMapping("/api/v1/lists/{listId}/items/{id}")
    public ResponseDTO<TaskItem> update(@PathVariable int listId,
                                        @PathVariable int id,
                                        @RequestBody ItemListRequestDTO request) {
        TaskItem taskItem = taskItemService.update(listId, id, request);
        return new ResponseDTO<TaskItem>(taskItem);
    }
    
    @DeleteMapping("/api/v1/lists/{listId}/items/{id}")
    public void delete(@PathVariable int listId, @PathVariable int id) {
        taskItemService.delete(listId, id);
        
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