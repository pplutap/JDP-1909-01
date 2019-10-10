package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testSave() {
        //Given
        Group group = groupRepository.save(new Group(null, "Chemia"));
        //When
        int groupSize = groupRepository.findAll().size();
        //Then
        assertEquals(1, groupSize);
    }

    @Test
    public void testGet() {
        //Given
        Group group = groupRepository.save(new Group(null, "Chemia"));
        //When
        Optional<Group> group2 = groupRepository.findById(group.getId());
        //Then
        assertEquals("Chemia", group2.get().getName());
    }

    @Test
    public void testUpdate() {
        //Given
        Group group = groupRepository.save(new Group(null, "Chemia"));
        groupRepository.save(new Group(group.getId(), "Nowa Chemia"));
        //When
        Optional<Group> updatedGroup = groupRepository.findById(group.getId());
        //Then
        assertEquals("Nowa Chemia", updatedGroup.get().getName());
        assertEquals(1, groupRepository.findAll().size());
    }

    @Test
    public void testDelete() {
        //Given
        Group group = groupRepository.save(new Group(1L, "Chemia"));
        //When
        groupRepository.deleteById(group.getId());
        //Then
        assertEquals(0, groupRepository.findAll().size());
    }
}
