package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<User>();
        userDao.findAll().forEach(user -> users.add(user));
        return users;
    }

    public User findById(Integer userId){
        return userDao.findById(userId).get();
    }

    public void create(User user){
        userDao.save(user);
    }

    public void delete(Integer userId){
        userDao.deleteById(userId);
    }

    public void update(User user,Integer userId){
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
