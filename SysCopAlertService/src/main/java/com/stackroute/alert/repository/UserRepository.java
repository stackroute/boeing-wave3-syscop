package com.stackroute.alert.repository;


import com.stackroute.alert.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "SELECT * FROM users WHERE username=?1", nativeQuery = true)
    public User getUserDetails(String username);

}