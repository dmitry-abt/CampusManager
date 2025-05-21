package com.example.campus_manager.service;

import com.example.campus_manager.entity.GroupEntity;
import com.example.campus_manager.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Page<GroupEntity> getAllGroups(Pageable pageable) {
        return groupRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public GroupEntity createGroup(String name) {
        GroupEntity group = new GroupEntity(name);

        return groupRepository.save(group);
    }

    public GroupEntity getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }
}