package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserRead() {
        //Given
        User user1 = new User(20L, "Waldek", "wald434", 1, 144456L, null, null);

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
        User user1 = new User(20L, "Jan", "jan999", 1, 123456L, null, null);

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
        User user1 = new User(1L, "Jerzy", "jurek32", 1, 987654L, null, null);
        User user2 = userRepository.save(user1);
        long id = user2.getId();
        user1 = new User(id, "Henryk", "henry321", 1, 987654L, null, null);
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
        User user1 = new User(1L, "Kuba", "kubi567", 1, 654321L, null, null);

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
