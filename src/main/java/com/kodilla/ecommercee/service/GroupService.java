package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exception.NotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(final GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group getGroup(final Long id) {
        return groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product group with id=" + id + " doesn't exist.")
        );
    }

}
