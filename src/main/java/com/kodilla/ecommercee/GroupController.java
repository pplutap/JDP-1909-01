package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.GroupDto;
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
public class GroupController {

    private List<GroupDto> sampleGroups = new ArrayList<>(
            Arrays.asList(
                    new GroupDto(1L, "Ubrania"),
                    new GroupDto(2L, "Dodatki"),
                    new GroupDto(3L, "Biżuteria"),
                    new GroupDto(4L, "Obuwie")
            ));

    @GetMapping
    public List<GroupDto> getAllGroups() {
        return sampleGroups;
    }

    @GetMapping("/{id}")
    public GroupDto getGroup(@PathVariable Long id) {
        return sampleGroups.stream()
                .filter(groupDto -> groupDto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    @PostMapping
    public GroupDto createGroup(@RequestBody GroupDto groupDto) {
        long maxId = sampleGroups.stream()
                .map(GroupDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        groupDto.setId(maxId + 1);
        sampleGroups.add(groupDto);
        return groupDto;
    }

    @PutMapping
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        GroupDto oldGroupDto = sampleGroups.stream()
                .filter(group -> group.getId().equals(groupDto.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Group not found"));
        sampleGroups.remove(oldGroupDto);
        sampleGroups.add(groupDto);
        return groupDto;
    }

}
