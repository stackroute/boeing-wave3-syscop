package com.stackroute.datacollectorservice.repository;

import com.stackroute.datacollectorservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface DataCollectorRepository extends MongoRepository<User, String> {

    @Query("{'applications.ipAddress': ?0}")
    public User findUser(String applicationIP);
}
