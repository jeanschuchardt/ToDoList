package com.jb.ItemService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class TaskList extends BaseEntity {
    @Column
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "id",nullable = false)
    private User user;
    
    @OneToMany(mappedBy = "taskList")
    private Set<TaskItem> items;
    
    
}
