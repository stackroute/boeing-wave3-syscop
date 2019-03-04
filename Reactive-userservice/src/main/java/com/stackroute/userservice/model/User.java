package com.stackroute.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.management.relation.Role;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementations of user model.
 */
@Document(collection = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    /**
     * User model class contains various variables:
     * id: assigned unique id
     * username: username of user
     * password:password of user
     * phoneNumber: phone no of user
     * email :email of user
     * company: company name of user
     * dob: date of birth
     * roles: roles of user
     */
    @Id
    private String id;
    @NotBlank
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String company;
    private String dob;
    private Set<Role> roles = new HashSet<>();

    /**
     * constructor for User class.
     * @param text
     */
    public User(String text) {
        this.username = text;
    }

    /**
     * get method of user class.
     * @return id of user.
     */
    public Object getId() {
        return id;
    }
}
