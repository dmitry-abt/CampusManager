package com.example.campus_manager.repository;

import com.example.campus_manager.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findByGroupIdOrderByFullNameAsc(Long groupId);
    Page<StudentEntity> findByGroupIdOrderByFullNameAsc(Long groupId, Pageable pageable);
    void deleteById(Long id);
}