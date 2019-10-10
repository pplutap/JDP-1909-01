package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupTest {

    @Autowired
    private GroupRepository groupRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testSave() {
        //Given
        groupRepository.save(new Group(null, "Chemia"));
        //When
        long size = groupRepository.count();
        //Then
        assertEquals(1L, size);
    }

    @Test
    public void testSaveWrongData() {
        //Given
        Exception exception = null;
        //When
        try {
            groupRepository.save(new Group(null, null));
            entityManager.flush();
        } catch (Exception e) {
            exception = e;
        }
        //Then
        assertThat(exception, is(notNullValue()));
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
        long size = groupRepository.count();
        //Then
        assertEquals("Nowa Chemia", updatedGroup.get().getName());
        assertEquals(1L, size);
    }

    @Test
    public void testDelete() {
        //Given
        Group group = groupRepository.save(new Group(1L, "Chemia"));
        //When
        groupRepository.deleteById(group.getId());
        long size = groupRepository.count();
        //Then
        assertEquals(0L, size);
    }
}
