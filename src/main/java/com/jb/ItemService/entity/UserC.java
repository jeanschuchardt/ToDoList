package com.jb.ItemService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class UserC extends BaseEntity {
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String login;
    @Column
    private String password;
    
//    @OneToMany(mappedBy = "user")
//    private Set<TaskList> taskList;
    
//    @OneToMany(mappedBy = "assignedUser")
//    private Set<TaskItem> itemsAssigned;
    
}
