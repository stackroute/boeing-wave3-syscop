package com.stackroute.AppRegistration.service;

import com.stackroute.AppRegistration.domain.User;
import com.stackroute.AppRegistration.exceptions.ApplicationAlreadyExistException;
import com.stackroute.AppRegistration.exceptions.ApplicationDoesNotExistException;

public interface ApplicationService {

    public User addApplication(User userObj) throws ApplicationAlreadyExistException;
    public User getAllApplications(String userName) throws ApplicationDoesNotExistException;
    public User updateApplications(User userObj) throws ApplicationDoesNotExistException;
    public User deleteApplication(User userObj) throws ApplicationDoesNotExistException;
}
