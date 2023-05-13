package com.jb.ItemService.service;

import com.jb.ItemService.entity.TaskItem;
import com.jb.ItemService.exception.ApiRequestException;
import com.jb.ItemService.record.ItemListRequestDTO;
import com.jb.ItemService.repository.TaskItemRepository;
import com.jb.ItemService.repository.TaskListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskItemService {
    
    
    private final TaskListRepository taskListRepository;
    
    
    private final TaskItemRepository taskItemRepository;
    
    public TaskItemService(TaskListRepository taskListRepository, TaskItemRepository taskItemRepository) {
        this.taskListRepository = taskListRepository;
        this.taskItemRepository = taskItemRepository;
    }
    
    public void deleteItemByListId(int id) {
        List<TaskItem> allByTaskListIdAndIsArchived = taskItemRepository.findAllByTaskListIdAndIsArchived(id, false);
        
        for (TaskItem taskItem : allByTaskListIdAndIsArchived) {
            taskItem.setIsArchived(true);
            saveItem(taskItem);
        }
    }
    
    public List<TaskItem> getItemsByList(int id) {
        List<TaskItem> allByTaskListIdAndIsArchived = taskItemRepository.findAllByTaskListIdAndIsArchived(id, false);
        if (allByTaskListIdAndIsArchived.isEmpty()) {
            throw new ApiRequestException("There is no items for list id " + id, HttpStatus.NO_CONTENT);
        }
        return allByTaskListIdAndIsArchived;
    }
    
    public TaskItem create(int listId, ItemListRequestDTO request) {
        isListPresent(listId);
        TaskItem taskItem = new TaskItem().setName(request.title())
                                          .setDescription(request.description())
                                          .setTaskListId((long) listId)
                                          .setAssignedUserId(request.userId())
                                          .setParentTaskId(request.parentTaskId());
        
        
        return taskItemRepository.save(taskItem);
    }
    
    
    public TaskItem update(int listId, int itemId, ItemListRequestDTO request) {
        isListPresent(listId);
        isItemPresent(itemId);
        
        TaskItem taskItem = taskItemRepository.findByIdAndIsArchived(itemId, false).get();
        
        
        taskItem.setName(request.title())
                .setDescription(request.description())
                .setParentTaskId(request.parentTaskId())
                .setAssignedUserId(request.userId());
        
        return saveItem(taskItem);
        
        
    }
    
    private TaskItem saveItem(TaskItem taskItem) {
        try {
            return taskItemRepository.save(taskItem);
        } catch (Exception e) {
            throw new ApiRequestException("No able to save item " + taskItem.getId() + ".", HttpStatus.BAD_REQUEST);
        }
    }
    
    
    public boolean isItemExist(int id) {
        return taskItemRepository.findByIdAndIsArchived(id, false).isPresent();
    }
    
    public void isItemPresent(int id) {
        if (!isItemExist(id)) {
            throw new ApiRequestException("Item with id " + id + " does not exist.", HttpStatus.NOT_FOUND);
        }
    }
    
    public void delete(int listId, int id) {
        
        TaskItem item = getItemNotArchivedByIdAndListId(listId, id);
        item.setIsArchived(true);
        try {
            saveItem(item);
        } catch (Exception e) {
            throw new ApiRequestException("Not able to delete item requested.", HttpStatus.BAD_REQUEST);
        }
        
    }
    
    private TaskItem getItemNotArchivedByIdAndListId(int listId, int id) {
        return taskItemRepository.findByIdAndTaskListIdAndIsArchived(id, listId, false)
                                 .orElseThrow(() -> new ApiRequestException("Item", HttpStatus.NOT_FOUND));
        
    }
    
    
    public boolean isListExist(int id) {
        return taskListRepository.findByIdAndIsArchived(id, false).isPresent();
    }
    
    public void isListPresent(int id) {
        if (!isListExist(id)) {
            throw new ApiRequestException("List with id " + id + " does not exist.", HttpStatus.NOT_FOUND);
        }
    }
}
