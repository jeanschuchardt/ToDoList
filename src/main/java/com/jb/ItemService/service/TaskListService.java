package com.jb.ItemService.service;

import com.jb.ItemService.entity.TaskList;
import com.jb.ItemService.repository.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskListService {
    
    private final TaskListRepository taskListRepository;
    private final TaskItemService taskItemService;
    
    public TaskListService(TaskListRepository taskListRepository, TaskItemService taskItemService) {
        this.taskListRepository = taskListRepository;
        this.taskItemService = taskItemService;
    }
    
    public String test(String text) {
        return "Hi user " + text;
    }
    
    public TaskList createList(String name) {
        TaskList taskList = new TaskList();
        taskList.setName(name);
    
        TaskList save = taskListRepository.save(taskList);
        
        return save;
    }
    
    public void delete(int id) {
        Optional<TaskList> byIdAndIsArchived = taskListRepository.findByIdAndIsArchived(id, false);
        
        if(!byIdAndIsArchived.isPresent()){
            throw new RuntimeException("not found");
        }
        
        TaskList taskList = byIdAndIsArchived.get();
        taskList.setIsArchived(true);
        
        taskListRepository.save(taskList);
        taskItemService.deleteItemByListId(id);
        
    }
    
    public TaskList getListById(int id) {
        Optional<TaskList> byIdAndIsArchived = taskListRepository.findByIdAndIsArchived(id, false);
        if(!byIdAndIsArchived.isPresent()){
            throw new RuntimeException("not found");
        }
        
        return byIdAndIsArchived.get();
    }
}
