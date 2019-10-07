package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {
    @Autowired
    private DbService dbService;
    @Autowired
    private GroupMapper groupMapper;

    @GetMapping(value = "getGroups")
    public List<GroupDto> getAllGroups() {
        return groupMapper.mapToGroupDtoList(dbService.getAllGroups());
    }

    @GetMapping(value = "getGroup={id}")
    public GroupDto getGroup(@PathVariable Long id) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(dbService.getGroupById(id).orElseThrow(GroupNotFoundException::new));
    }

    @PostMapping(value = "createGroup", consumes = APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
        dbService.saveGroup(groupMapper.mapToGroup(groupDto));
    }

    @PutMapping(value = "updateGroup",consumes = APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return groupMapper.mapToGroupDto(dbService.saveGroup(groupMapper.mapToGroup(groupDto)));
    }

}
