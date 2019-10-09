package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTest {

    @Autowired
    private GroupRepository groupRepository;

    @After
    public void clearRepository(){
        groupRepository.findAll().clear();
    }

    @Test
    public void testSave() {
        //Given
        Group group = new Group(1L, "Chemia");
        groupRepository.save(group);
        //When
        int groupSize = groupRepository.findAll().size();
        //Then
        assertEquals(1, groupSize);
    }

    @Test
    public void testGet() {
        //Given
        Group group = new Group(1L, "Chemia");
        groupRepository.save(group);
        //When
        Optional<Group> group2 = groupRepository.findById(group.getId());
        //Then
        assertEquals("Chemia", group2.get().getName());
    }

    @Test
    public void testUpdate() {
        //Given
        Group group = new Group(1L, "Chemia");
        groupRepository.save(group);
        group = new Group(groupRepository.findAll().get(0).getId(), "Nowa Chemia");
        groupRepository.save(group);
        //When
        Optional<Group> updatedGroup = groupRepository.findById(group.getId());
        //Then
        assertEquals("Nowa Chemia", updatedGroup.get().getName());
        assertEquals(1, groupRepository.findAll().size());
    }

    @Test
    public void testDelete() {
        //Given
        Group group = new Group(1L, "Chemia");
        groupRepository.save(group);
        //When
        groupRepository.deleteById(group.getId());
        //Then
        assertEquals(0, groupRepository.findAll().size());
    }
}
