package com.jb.ItemService.repository;

import com.jb.ItemService.entity.TaskItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskItemRepository extends BaseRepository<TaskItem> {
    
    List<TaskItem> findAllByTaskListIdAndIsArchived(int taskListId, boolean isArchived);
    
    Optional<TaskItem> findByIdAndTaskListIdAndIsArchived(int itemId, int taskListId, boolean isArchived);
    
}
