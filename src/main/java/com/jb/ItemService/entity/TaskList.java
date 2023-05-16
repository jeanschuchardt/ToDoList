package com.jb.ItemService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class TaskList extends BaseEntity {
    @Column
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "userId",updatable = false,insertable = false)
    private User user;
    
    @Column(nullable = false)
    private Long userId;
    @OneToMany(mappedBy = "taskList")
    @JsonIgnore
    private Set<TaskItem> items;
    
    
}
