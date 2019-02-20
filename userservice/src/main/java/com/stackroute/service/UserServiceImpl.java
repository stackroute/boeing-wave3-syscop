package com.stackroute.service;

import com.stackroute.model.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Primary
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //for saving data Post
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(user.getUsername())) {

            throw new UserAlreadyExistsException("User already exists");
        }

        User user1 = userRepository.save(user);
        if (user1 == null) {
            throw new UserAlreadyExistsException("User already exists!!try to register new user");
        }
        return user1;
    }

    //for getting data Get
    public List<User> getAllUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        return userList;
    }

    //for deleting the user Delete
    public User deleteUser(String userId) throws UserNotFoundException {
        User user1 = userRepository.findById(userId).get();
        if (userRepository.existsById(userId))
            userRepository.deleteById(userId);
        else {
            throw new UserNotFoundException("User not found to delete the user");
        }
        return user1;

    }

    //for updating the user Put
    public User updateUser(String userId, User user) throws UserNotFoundException {
        if (userRepository.existsById(userId)) {
            User user1 = userRepository.save(user);
            return user1;
        } else
            throw new UserNotFoundException("User not found to update");

    }
}


