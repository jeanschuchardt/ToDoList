package com.jb.ItemService.service;

import com.jb.ItemService.entity.TaskItem;
import com.jb.ItemService.entity.User;
import com.jb.ItemService.exception.ApiRequestException;
import com.jb.ItemService.record.ItemListRequestDTO;
import com.jb.ItemService.repository.TaskItemRepository;
import com.jb.ItemService.repository.TaskListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskItemService {
    
    private final TaskListRepository taskListRepository;
    
    private final TaskItemRepository taskItemRepository;
    
    private final UserService userService;
    
    public TaskItemService(TaskListRepository taskListRepository, TaskItemRepository taskItemRepository,
                           UserService userService) {
        this.taskListRepository = taskListRepository;
        this.taskItemRepository = taskItemRepository;
        this.userService = userService;
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
    
        validateParentTask(listId, request.parentTaskId());
    
        TaskItem taskItem = new TaskItem().setName(request.title())
                                          .setDescription(request.description())
                                          .setTaskListId((long) listId)
                                          .setAssignedUserId(request.userId())
                                          .setParentTaskId(request.parentTaskId());
        
        
        return taskItemRepository.save(taskItem);
    }
    
    private void validateParentTask(int listId, Long parentTaskId ) {
        
        if (Objects.nonNull(parentTaskId)) {
            Optional<TaskItem> byIdAndIsArchived = taskItemRepository.findByIdAndIsArchived(Math.toIntExact(parentTaskId), false);
            if (byIdAndIsArchived.isPresent()) {
                TaskItem taskItem = byIdAndIsArchived.get();
                Long taskListId = taskItem.getTaskListId();
                if (taskListId.intValue() != listId) {
                    throw new ApiRequestException("Item parent id not belong to a item under this list.",
                            HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new ApiRequestException("Item parent id not found.",
                        HttpStatus.NOT_FOUND);
            }
        }
    }
    
    
    public TaskItem update(int listId, int itemId, ItemListRequestDTO request) {
        isListPresent(listId);
        isItemPresent(itemId);
        
        TaskItem taskItem = taskItemRepository.findByIdAndIsArchived(itemId, false).get();
        
        validateParentTask(listId, request.parentTaskId());
        
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
        
        Long userId = item.getTaskList().getUserId();
        Long assignedUserId = item.getAssignedUserId();
    
        validateCurrentUser(userId, assignedUserId);
        try {
            saveItem(item);
        } catch (Exception e) {
            throw new ApiRequestException("Not able to delete item requested.", HttpStatus.BAD_REQUEST);
        }
        
    }
    
    private void validateCurrentUser(Long userId, Long assignedUserId) {
        User authenticatedUser = userService.getAuthenticatedUser();
        
        if (!authenticatedUser.getId().equals(userId) &&
                !(Objects.nonNull(assignedUserId)
                        || authenticatedUser.equals(assignedUserId))
        ) {
            throw new ApiRequestException("User can not execute action  item.", HttpStatus.FORBIDDEN);
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
