package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO сделать красивый html logina regis , везде поменять навигацию создать carorder
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
