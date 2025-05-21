package com.example.campus_manager.controller;

import com.example.campus_manager.entity.GroupEntity;
import com.example.campus_manager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String listGroups(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "" + DEFAULT_PAGE_SIZE) int size,
            Model model) {

        Page<GroupEntity> groupPage = groupService.getAllGroups(PageRequest.of(page, size));
        model.addAttribute("groups", groupPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", groupPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "groups/list";
    }

    @PostMapping
    public String addGroup(@RequestParam String name) {
        GroupEntity group = groupService.createGroup(name);

        return "redirect:/groups/" + group.getId() + "/students";
    }
}