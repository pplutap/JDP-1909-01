package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@CrossOrigin(origins = "*")
public class GroupController {

    @GetMapping
    public List<GroupDto> getAllGroups() {
        return getSampleData();
    }

    private List<GroupDto> getSampleData() {
        return new ArrayList<>(
                Arrays.asList(
                        new GroupDto(1L, "Ubrania"),
                        new GroupDto(2L, "Dodatki"),
                        new GroupDto(3L, "Biżuteria"),
                        new GroupDto(4L, "Obuwie")
                ));
    }

    @GetMapping("/{id}")
    public GroupDto getGroup(@PathVariable Long id) {
        return getSampleData().get(0);
    }

    @PostMapping
    public GroupDto createGroup(@RequestBody GroupDto group) {
        return group;
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto group) {
        return group;
    }

}
