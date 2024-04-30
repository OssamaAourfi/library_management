package com.librarymanagementsystem.service;

import com.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
