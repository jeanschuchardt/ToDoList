package com.jb.ItemService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class User extends BaseEntity {
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String login;
    @Column
    private String password;
    
    @OneToMany(mappedBy = "user")
    private Set<TaskList> taskList;
    
    @OneToMany(mappedBy = "assignedUser")
    private Set<TaskItem> itemsAssigned;
    
}
