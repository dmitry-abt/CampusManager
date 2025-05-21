package com.example.campus_manager.service;

import com.example.campus_manager.entity.GroupEntity;
import com.example.campus_manager.entity.StudentEntity;
import com.example.campus_manager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupService groupService;

    @Autowired
    public StudentService(StudentRepository studentRepository, GroupService groupService) {
        this.studentRepository = studentRepository;
        this.groupService = groupService;
    }

    public List<StudentEntity> getStudentsByGroupId(Long groupId) {
        return studentRepository.findByGroupIdOrderByFullNameAsc(groupId);
    }

    public Page<StudentEntity> getStudentsByGroupId(Long groupId, Pageable pageable) {
        return studentRepository.findByGroupIdOrderByFullNameAsc(groupId, pageable);
    }

    public void addStudentToGroup(Long groupId, String fullName) {
        GroupEntity group = groupService.getGroupById(groupId);
        if (group == null)
            throw new IllegalArgumentException("Группа не найдена!");

        StudentEntity student = new StudentEntity(fullName, group);
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}