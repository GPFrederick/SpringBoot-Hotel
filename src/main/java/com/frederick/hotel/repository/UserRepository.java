package com.frederick.hotel.repository;

import com.frederick.hotel.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<User> findByEmail(String url);
    User findByUsername(String username);
    User findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.firstName LIKE CONCAT('%', :query, '%')")
    List<User> searchUsers(String query);
    User findFirstByUsername(String username);
}
