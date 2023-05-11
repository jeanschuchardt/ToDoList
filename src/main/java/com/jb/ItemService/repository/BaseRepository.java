package com.jb.ItemService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
    
    Optional<T> findByIdAndIsArchived(Integer id, boolean isArchived);
}
