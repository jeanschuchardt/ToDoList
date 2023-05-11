package com.jb.ItemService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TaskItem  extends BaseEntity{
    @Column
    private String name;
    
    @Column
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "taskListId",nullable = false,insertable = false,updatable = false)
    private TaskList taskList;
    
    @Column
    private Integer taskListId;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User assignedUser;
    
    @OneToOne(optional = true)
    @JoinColumn(name = "parentTaskId",insertable = false,updatable = false)
    private TaskItem parentTask;
    
    @Column
    private Integer parentTaskId;
    
    
    
    
}
