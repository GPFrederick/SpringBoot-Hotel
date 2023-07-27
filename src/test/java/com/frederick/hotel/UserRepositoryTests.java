package com.frederick.hotel;

import com.frederick.hotel.controller.UserController;
import com.frederick.hotel.models.User;
import com.frederick.hotel.repository.UserRepository;
import com.frederick.hotel.service.UserService;
import com.frederick.hotel.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {

        User user = new User();
        user.setEmail("fgilp95@gmail.com");
        user.setPassword("123456789");
        user.setFirstName("Fred");
        user.setLastName("P.");

    }
}
