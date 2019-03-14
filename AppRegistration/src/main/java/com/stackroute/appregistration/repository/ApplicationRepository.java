package com.stackroute.appregistration.repository;

import com.stackroute.appregistration.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Application repository is an interface that extends MongoRepository
 * Here, we can also define our custom queries apart from the ones
 * that already exists in mongo repository
 *
 * @author Shri Ramya A
 */

public interface ApplicationRepository extends MongoRepository<User,String> {

}
