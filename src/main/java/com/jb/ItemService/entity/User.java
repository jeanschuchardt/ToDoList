package com.jb.ItemService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User extends BaseEntity implements UserDetails {
    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    @JsonIgnore
    private String password;
    
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<TaskList> taskList = new HashSet<>();

    @OneToMany(mappedBy = "assignedUser")
    @JsonIgnore
    private Set<TaskItem> itemsAssigned  = new HashSet<>();
    
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Role role;
    
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    
    @Override
    public String getUsername() {
        return email;
    }
    
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
