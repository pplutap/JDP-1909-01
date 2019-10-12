package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {


    @Autowired
    private UserRepository userRepository;


    @Test
    public void testUserRead() {
        //Given
        User user1 = new User(20l, "Waldek", 1, 144456L);

        //When
        User user2 = userRepository.save(user1);

        //Then
        long id = user2.getId();
        Optional<User> readUser = userRepository.findById(id);
        String  userName = readUser.get().getUserName();
        Assert.assertEquals("Waldek", userName);

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testUserAdd() {
        //Given
        User user1 = new User(20l, "Jan", 1, 123456L);

        //When
        User user2 = userRepository.save(user1);

        //Then
        long id = user2.getId();
        Optional<User> readUser = userRepository.findById(id);
        Assert.assertTrue(readUser.isPresent());

        //CleanUp
        userRepository.deleteById(id);

    }


    @Test
    public void testUserUpdate() {
        //Given
        User user1 = new User(1l,"Jerzy", 1, 987654L);
        User user2 = userRepository.save(user1);
        long id = user2.getId();
        user1 = new User(id,"Henryk", 1, 987654L);
        userRepository.save(user1);

        //When
        Optional<User> readUser = userRepository.findById(id);

        //Then
       Assert.assertEquals("Henryk", readUser.get().getUserName());

        //CleanUp
        userRepository.deleteById(id);


    }
    @Test
    public void testUserDelete() {
        //Given
        User user1 = new User(1l, "Kuba", 1, 654321L);

        //When
        User user2 = userRepository.save(user1);

        //Then
        long id = user2.getId();
        Optional<User> readUser = userRepository.findById(id);
        Assert.assertTrue(readUser.isPresent());
        userRepository.deleteById(id);
        Optional<User> deleteUser = userRepository.findById(id);
        Assert.assertFalse(deleteUser.isPresent());

    }

}
