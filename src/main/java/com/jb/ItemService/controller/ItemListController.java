package com.jb.ItemService.controller;

import com.jb.ItemService.entity.TaskItem;
import com.jb.ItemService.record.ItemListRequestDTO;
import com.jb.ItemService.record.ResponseDTO;
import com.jb.ItemService.record.ResponseListDTO;
import com.jb.ItemService.service.TaskItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemListController {
    
    public final TaskItemService taskItemService;
    
    public ItemListController(TaskItemService taskItemService) {
        this.taskItemService = taskItemService;
        
    }
    
    @Operation(summary = "Get items of a list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @GetMapping("/api/v1/lists/{id}/items")
    public ResponseListDTO<TaskItem> get(@PathVariable int id) {
        List<TaskItem> itemsByList = taskItemService.getItemsByList(id);
        return new ResponseListDTO<>(itemsByList);
    }
    
    @Operation(summary = "Create new list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @PostMapping("/api/v1/lists/{id}/items")
    public ResponseDTO<TaskItem> post(@PathVariable int id, @RequestBody ItemListRequestDTO request) {
        TaskItem taskItem = taskItemService.create(id, request);
        return new ResponseDTO<>(taskItem);
    }
    
    @Operation(summary = "Update list", description = "Only the owner can update the list, any other user will fail " +
            "the execution.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @PutMapping("/api/v1/lists/{listId}/items/{id}")
    public ResponseDTO<TaskItem> update(@PathVariable int listId,
                                        @PathVariable int id,
                                        @RequestBody ItemListRequestDTO request) {
        TaskItem taskItem = taskItemService.update(listId, id, request);
        return new ResponseDTO<>(taskItem);
    }
    
    @Operation(summary = "Delete list", description = "Only the owner can update the list, any other user will fail " +
            "the execution.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @DeleteMapping("/api/v1/lists/{listId}/items/{id}")
    public void delete(@PathVariable int listId, @PathVariable int id) {
        taskItemService.delete(listId, id);
        
    }
}