package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {
    private final GroupService groupService;
    private final GroupMapper groupMapper;

    public GroupController(final GroupService groupService, final GroupMapper groupMapper) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
    }

    @GetMapping
    public List<GroupDto> getAllGroups() {
        return groupMapper.mapToGroupDtoList(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public GroupDto getGroup(@PathVariable Long id) {
        return groupMapper.mapToGroupDto(groupService.getGroupById(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public GroupDto createGroup(@RequestBody GroupDto groupDto) {
        return groupMapper.mapToGroupDto(groupService.createGroup(groupMapper.mapToGroup(groupDto)));
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return groupMapper.mapToGroupDto(groupService.updateGroup(groupMapper.mapToGroup(groupDto)));
    }
}
