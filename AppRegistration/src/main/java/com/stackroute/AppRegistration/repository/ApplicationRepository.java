package com.stackroute.AppRegistration.repository;

import com.stackroute.AppRegistration.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationRepository extends MongoRepository<User,String> {

}
