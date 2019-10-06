package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.GenericEntityRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testSaveAndGet() {
        //Given
        Group group = new Group(1L, "Chemia");
        groupRepository.save(group);
        //When
        Optional<Group> group2 = groupRepository.findById(group.getId());
        //Then
        assertEquals("Chemia", group2.get().getName());
    }
}
