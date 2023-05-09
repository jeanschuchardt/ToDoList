package com.jb.ItemService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TaskItem  extends BaseEntity{
    @Column
    private String name;
    
    @Column
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "id",nullable = false)
    private TaskList taskList;
    
    @ManyToOne
    @JoinColumn(name = "id")
    private User assignedUser;
    
}
