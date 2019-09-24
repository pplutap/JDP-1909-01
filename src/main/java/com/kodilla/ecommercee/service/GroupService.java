package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GroupService {

    private List<GroupDto> sampleGroups = new ArrayList<>(
            Arrays.asList(
                    new GroupDto(1L, "Ubrania"),
                    new GroupDto(2L, "Dodatki"),
                    new GroupDto(3L, "Biżuteria"),
                    new GroupDto(4L, "Obuwie")
            ));

    public List<GroupDto> getGroups() {
        return sampleGroups;
    }

    public GroupDto getGroup(final Long id) {
        return sampleGroups.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public GroupDto createGroup(final GroupDto groupDto) {
        long maxId = sampleGroups.stream()
                .map(GroupDto::getId)
                .max(Long::compareTo)
                .orElse(0L);
        groupDto.setId(maxId + 1);
        sampleGroups.add(groupDto);
        return groupDto;
    }

    public GroupDto updateGroup(final GroupDto groupDto) {
        GroupDto oldGroupDto = sampleGroups.stream()
                .filter(g -> g.getId().equals(groupDto.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Group not found"));
        sampleGroups.remove(oldGroupDto);
        sampleGroups.add(groupDto);
        return groupDto;
    }

}
