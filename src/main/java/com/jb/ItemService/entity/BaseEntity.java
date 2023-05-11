package com.jb.ItemService.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dateCreated;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date dateUpdated;
    
    @Column
    private Integer createdBy;
    
    @Column
    private Integer updatedBy;
    
    @Column
    private Boolean isArchived;
    
    
 
}
