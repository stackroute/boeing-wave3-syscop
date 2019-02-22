package com.stackroute.AppRegistration.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * User is a domain object thet has list of applications
 * and username. This class also defines the collection that
 * is stored in mongo database
 *
 * @author Shri Ramya A
 */

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userName;
    private List<Application> applications;
}
