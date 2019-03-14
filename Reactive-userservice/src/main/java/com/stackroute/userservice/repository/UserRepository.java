package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * repository class for UserService.
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository <User, String> {
}
