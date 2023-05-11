package com.jb.ItemService.service;

import com.jb.ItemService.entity.TaskItem;
import com.jb.ItemService.entity.TaskList;
import com.jb.ItemService.repository.TaskItemRepository;
import com.jb.ItemService.repository.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskItemService {
    
    private final TaskItemRepository taskItemRepository;
    
    public TaskItemService(TaskItemRepository taskItemRepository) {
        this.taskItemRepository = taskItemRepository;
    }
    
    public void deleteItemByListId(int id) {
        List<TaskItem> allByTaskListIdAndIsArchived = taskItemRepository.findAllByTaskListIdAndIsArchived(id, false);
    
        for (TaskItem taskItem : allByTaskListIdAndIsArchived) {
            taskItem.setIsArchived(true);
            taskItemRepository.save(taskItem);
        }
    }
}