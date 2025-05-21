package com.example.campus_manager.repository;

import com.example.campus_manager.entity.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    Page<GroupEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}