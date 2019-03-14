package com.stackroute.appregistration.service;

import com.stackroute.appregistration.domain.User;
import com.stackroute.appregistration.exceptions.ApplicationAlreadyExistException;
import com.stackroute.appregistration.exceptions.ApplicationDoesNotExistException;

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
