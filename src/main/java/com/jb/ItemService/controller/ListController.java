package com.jb.ItemService.controller;

import com.jb.ItemService.entity.TaskList;
import com.jb.ItemService.record.ListRequestDTO;
import com.jb.ItemService.record.TaskListResponseDTO;
import com.jb.ItemService.service.TaskListService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ListController {
    
    private final TaskListService taskListService;
    
    private final HttpServletRequest httpServletRequest;
    
    @GetMapping("/api/v1/lists")
    public List<TaskListResponseDTO> getListById() {
        List<TaskList> all = taskListService.getAll();
        List<TaskListResponseDTO> responseDTOS =  new ArrayList<>();
        for (TaskList taskList : all) {
            TaskListResponseDTO taskListResponseDTO = taskListService.mapResponse(taskList);
            responseDTOS.add(taskListResponseDTO);
        }
        return responseDTOS;
    }
    @GetMapping("/api/v1/lists/{id}")
    public TaskListResponseDTO getListById(@PathVariable int id) {
        TaskList listById = taskListService.getListById(id);
        TaskListResponseDTO taskListResponseDTO = taskListService.mapResponse(listById);
        return taskListResponseDTO;
    }
    
    @PostMapping("/api/v1/lists")
    public TaskListResponseDTO post(@RequestBody ListRequestDTO requestDTO) {
        TaskList list = taskListService.createList(requestDTO);
        list = taskListService.getListById(list.getId().intValue());
        TaskListResponseDTO taskListResponseDTO = taskListService.mapResponse(list);
        return taskListResponseDTO;
    }
    
    @DeleteMapping("/api/v1/lists/{id}")
    public void delete(@PathVariable int id) {
        taskListService.delete(id);
        
    }
}
