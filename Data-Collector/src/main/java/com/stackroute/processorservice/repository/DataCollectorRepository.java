package com.stackroute.processorservice.repository;

import com.stackroute.processorservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DataCollectorRepository extends MongoRepository<User, String> {

    @Query("{'applications.ipAddress': ?0}")
    public User findUser(String applicationIP);
}
