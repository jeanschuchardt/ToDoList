package com.jb.ItemService.repository;

import com.jb.ItemService.entity.TaskList;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskListRepository extends BaseRepository<TaskList> {
    
    
    List<TaskList> findAllByUserIdAndIsArchived(Long id, boolean b);
}
