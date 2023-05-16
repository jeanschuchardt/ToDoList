package com.jb.ItemService.service;

import com.jb.ItemService.entity.TaskList;
import com.jb.ItemService.entity.User;
import com.jb.ItemService.exception.ApiRequestException;
import com.jb.ItemService.record.ListRequestDTO;
import com.jb.ItemService.repository.TaskListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskListService {
    
    private final TaskListRepository taskListRepository;
    private final TaskItemService taskItemService;
    private final UserService userService;
    
    public TaskListService(TaskListRepository taskListRepository, TaskItemService taskItemService,
                           UserService userService) {
        this.taskListRepository = taskListRepository;
        this.taskItemService = taskItemService;
        this.userService = userService;
    }
    
    public String test(String text) {
        return "Hi user " + text;
    }
    
    public TaskList createList(ListRequestDTO requestDTO) {
        
        TaskList taskList = new TaskList();
        taskList.setName(requestDTO.name());
        User authenticatedUser = userService.getAuthenticatedUser();
        taskList.setUserId(authenticatedUser.getId());
    
        TaskList save = save(taskList);
    
        return save;
    }
    
    private TaskList save(TaskList taskList) {
        try {
            TaskList save = taskListRepository.save(taskList);
            return save;
        }catch (Exception e){
            throw  new ApiRequestException("Unable to save list.", HttpStatus.BAD_REQUEST);
        }
    }
    
    public void delete(int id) {
        Optional<TaskList> byIdAndIsArchived = taskListRepository.findByIdAndIsArchived(id, false);
        
        if (!byIdAndIsArchived.isPresent()) {
            throw new RuntimeException("not found");
        }
        
        TaskList taskList = byIdAndIsArchived.get();
        User authenticatedUser = userService.getAuthenticatedUser();
        if(!authenticatedUser.getId().equals(taskList.getUser().getId())){
            throw  new ApiRequestException("You can not delete this list",HttpStatus.FORBIDDEN);
        }
        
        taskList.setIsArchived(true);
    
        save(taskList);
        taskItemService.deleteItemByListId(id);
        
    }
    
    public TaskList getListById(int id) {
        Optional<TaskList> byIdAndIsArchived = taskListRepository.findByIdAndIsArchived(id, false);
        if (!byIdAndIsArchived.isPresent()) {
            throw new RuntimeException("not found");
        }
        
        return byIdAndIsArchived.get();
    }
    
    
}
