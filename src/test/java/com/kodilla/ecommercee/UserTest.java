package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testUserReadId() {
        //Given
        User user = new User(1l, "Jan", 1, 123456L);

        //When
        userRepository.save(user);

        //Then
        long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        String userName = readUser.get().getUserName();
        Assert.assertEquals("Jan", userName);

    }

    @Test
    public void testUserAdd() {
        //Given
        User user = new User(1l, "Jan", 1, 123456L);

        //When
        userRepository.save(user);

        //Then
        long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        Assert.assertTrue(readUser.isPresent());

    }


    @Test
    public void testUserUpdate() {
        //Given
        User user = new User(1L, "Jan", 1, 123456L);
        userRepository.save(user);
        user = new User(userRepository.findAll().get(0).getId(),"Henryk", 1, 123456L);
        long id = user.getId();
        userRepository.save(user);

        //When
        Optional<User> readUser = userRepository.findById(id);

        //Then
       Assert.assertEquals("Henryk", readUser.get().getUserName());


    }
    @Test
    public void testUserDelete() {
        //Given
        User user = new User(1l, "Jan", 1, 123456L);

        //When
        userRepository.save(user);

        //Then
        long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        Assert.assertTrue(readUser.isPresent());
        userRepository.deleteById(id);
        Optional<User> deleteUser = userRepository.findById(id);
        Assert.assertFalse(deleteUser.isPresent());

    }

}
