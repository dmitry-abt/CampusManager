package com.example.campus_manager.controller;

import com.example.campus_manager.entity.StudentEntity;
import com.example.campus_manager.service.GroupService;
import com.example.campus_manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groups/{groupId}/students")
public class StudentController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping
    public String listStudents(
            @PathVariable Long groupId,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "" + DEFAULT_PAGE_SIZE) int size,
            Model model) {

        model.addAttribute("groupId", groupId);
        model.addAttribute("group", groupService.getGroupById(groupId));

        Page<StudentEntity> studentPage = studentService.getStudentsByGroupId(groupId, PageRequest.of(page, size));
        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "students/list";
    }

    @PostMapping
    public String addStudent(
            @PathVariable Long groupId,
            @RequestParam String fullName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "" + DEFAULT_PAGE_SIZE) int size) {

        studentService.addStudentToGroup(groupId, fullName);

        return "redirect:/groups/" + groupId + "/students?page=" + page + "&size=" + size;
    }

    @PostMapping("/{studentId}/delete")
    public String deleteStudent(
            @PathVariable Long groupId,
            @PathVariable Long studentId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "" + DEFAULT_PAGE_SIZE) int size) {

        studentService.deleteStudent(studentId);

        return "redirect:/groups/" + groupId + "/students?page=" + page + "&size=" + size;
    }
}