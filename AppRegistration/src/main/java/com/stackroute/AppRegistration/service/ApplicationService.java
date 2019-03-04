package com.stackroute.AppRegistration.service;

import com.stackroute.AppRegistration.domain.User;
import com.stackroute.AppRegistration.exceptions.ApplicationAlreadyExistException;
import com.stackroute.AppRegistration.exceptions.ApplicationDoesNotExistException;

/**
 * ApplicationService is an interface in the service layer
 * that has method signatures that needs to be implemented
 * that will be used in our application
 *
 * @author Shri Ramya A
 */

public interface ApplicationService {

    public User addApplication(User userObj) throws ApplicationAlreadyExistException;
    public User getAllApplications(String userName) throws ApplicationDoesNotExistException;
    public User updateApplications(User userObj) throws ApplicationDoesNotExistException;
    public User deleteApplication(User userObj) throws ApplicationDoesNotExistException;
}
