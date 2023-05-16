package com.jb.ItemService.controller;

import com.jb.ItemService.entity.TaskList;
import com.jb.ItemService.record.ListRequestDTO;
import com.jb.ItemService.service.TaskListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ListController {
    
    private final TaskListService taskListService;
    
    @Operation(summary = "Get all lists", description = "It will return all lists that belongs to the authenticated " +
            "user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @GetMapping("/api/v1/lists")
    public List<TaskList> getListById() {
        List<TaskList> all = taskListService.getAll();
        return all;
    }
    
    @Operation(summary = "Get list by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content)})
    @GetMapping("/api/v1/lists/{id}")
    public TaskList getListById(@PathVariable int id) {
        TaskList listById = taskListService.getListById(id);
        return listById;
    }
    
    @Operation(summary = "Create new list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
    @PostMapping("/api/v1/lists")
    public TaskList post(@RequestBody ListRequestDTO requestDTO) {
        TaskList list = taskListService.createList(requestDTO);
        return list;
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
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @DeleteMapping("/api/v1/lists/{id}")
    public void delete(@PathVariable int id) {
        taskListService.delete(id);
        
    }
}
