package com.stackroute.AppRegistration.repository;

import com.stackroute.AppRegistration.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Application Repository is an interface that extends MongoRepository
 * Here, we can also define our custom queries apart from the ones
 * that already exists in mongo repository
 *
 * @author Shri Ramya A
 */

public interface ApplicationRepository extends MongoRepository<User,String> {

}
