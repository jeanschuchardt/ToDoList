package com.jb.ItemService.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class TaskItem extends BaseEntity {
    @Column
    private String name;
    
    @Column
    private String description;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "taskListId", insertable = false, updatable = false)
    private TaskList taskList;
    
    @Column(nullable = false)
    private Long taskListId;
    
    @ManyToOne
    @JoinColumn(name = "assignedUserId", insertable = false, updatable = false)
    private User assignedUser;
    
    @Column
    private Long assignedUserId;
    
    @OneToOne(optional = true)
    @JoinColumn(name = "parentTaskId", insertable = false, updatable = false)
    private TaskItem parentTask;
    
    @Column(nullable = true)
    private Long parentTaskId;
    
    
}
