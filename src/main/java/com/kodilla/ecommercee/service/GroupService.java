package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(final GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(final Long id){
        return groupRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Group with id=" + id + " doesn't exist.")
        );
    }

    public Group createGroup(Group group) {
        if(group.getId()==null){
            return groupRepository.save(group);
        }

        boolean isPresent = groupRepository.findById(group.getId()).isPresent();
        if (isPresent) {
            throw  new RuntimeException("Group with id=" + group.getId() + " already exists!");
        }

        return groupRepository.save(group);
    }

    public Group updateGroup(final Group group) {
        if (group.getId()== null) {
            throw new RuntimeException("Id must not be null!");
        }

        boolean isPresent = groupRepository.findById(group.getId()).isPresent();
        if(!isPresent) {
            throw new RuntimeException("Group with id=" + group.getId() + " doesn't exist.");
        }
        return groupRepository.save(group);
    }
}
