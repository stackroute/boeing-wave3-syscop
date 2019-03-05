package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "SyscopUser")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    private String id;
    private String username ;
    private String password;
    private String phoneNumber;
    private String email;
    private String company;
    private String dob;
    private Set<Role> roles = new HashSet<>();
}

