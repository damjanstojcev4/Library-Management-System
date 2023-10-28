package com.damjan.librarymanagementsystem.repository;

import com.damjan.librarymanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // To list all the users for admins page
    List<User> findAll();

    // Search by first name
    List<User> findByFirst_name(String user_name);

    // Search by last name
    List<User> findByLast_name(String last_name);

    // Search by username
    List<User> findByUsername(String username);
}
