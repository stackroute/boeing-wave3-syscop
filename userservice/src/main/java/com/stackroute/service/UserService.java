package com.stackroute.service;

import com.stackroute.model.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

        public User saveUser (User user) throws UserAlreadyExistsException;
        public List<User> getAllUsers();
        public User deleteUser(String userId) throws UserNotFoundException;
        public User updateUser(String userId, User user) throws UserNotFoundException;
    }

