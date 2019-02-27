package com.stackroute.AppRegistration.service;

import com.stackroute.AppRegistration.domain.Application;
import com.stackroute.AppRegistration.domain.User;
import com.stackroute.AppRegistration.exceptions.ApplicationAlreadyExistException;
import com.stackroute.AppRegistration.exceptions.ApplicationDoesNotExistException;
import com.stackroute.AppRegistration.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ApplicationServiceImpl is a java class that extends the
 * interface ApplicationService. All the methods defined in the intergace
 * are implemented in this class
 *
 * @author Shri Ramya A
 */

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepositoryObj;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepositoryObj) {
        this.applicationRepositoryObj = applicationRepositoryObj;
    }

    @Override
    public User addApplication(User userObj) throws ApplicationAlreadyExistException{
        User returnedUser = new User();
        if (applicationRepositoryObj.existsById(userObj.getUserName())){
            User savedUser = applicationRepositoryObj.findById(userObj.getUserName()).get();
            returnedUser.setUserName(userObj.getUserName());
            List<Application> appList = savedUser.getApplications();
            List<Application> applicationToReturn = new ArrayList<>();
            Iterator applicationIterator = appList.iterator();
            while(applicationIterator.hasNext()) {
                Application savedApplicationFromDb = (Application) applicationIterator.next();
                applicationToReturn.add(savedApplicationFromDb);
            }
            applicationToReturn.add(userObj.getApplications().get(0));
            returnedUser.setApplications(applicationToReturn);
            applicationRepositoryObj.save(returnedUser);
        }
        else {
            returnedUser = applicationRepositoryObj.save(userObj);
        }
        return returnedUser;
    }

    @Override
    public User getAllApplications(String userName) throws ApplicationDoesNotExistException {
        User userObj;
        if (applicationRepositoryObj.existsById(userName)){
            userObj = applicationRepositoryObj.findById(userName).get();
        }
        else {
            throw new ApplicationDoesNotExistException("User hasn't registered for any application yet!");
        }
        return userObj;
    }

    @Override
    public User updateApplications(User userObj) throws ApplicationDoesNotExistException {
        User savedUser = applicationRepositoryObj.findById(userObj.getUserName()).get();
        Application appToBeUpdated = userObj.getApplications().get(0);
        String appNameToCompare = appToBeUpdated.getApplicationName();
        List<Application> applicationList = savedUser.getApplications();
        List<Application> updatedListOfApplications = new ArrayList<>();
        Iterator applicationIterator = applicationList.iterator();
        while (applicationIterator.hasNext()){
            Application appToBeComparedWith = (Application) applicationIterator.next();
            if (appToBeComparedWith.getApplicationName().equals(appNameToCompare)){
                updatedListOfApplications.add(appToBeUpdated);
            }
            else{
                updatedListOfApplications.add(appToBeComparedWith);
            }
        }
        User updatedUser = new User(userObj.getUserName(),updatedListOfApplications);
        applicationRepositoryObj.save(updatedUser);
        return updatedUser;
    }

    @Override
    public User deleteApplication(User userObj) throws ApplicationDoesNotExistException {
        User savedUser = applicationRepositoryObj.findById(userObj.getUserName()).get();
        Application appToBedeleted = userObj.getApplications().get(0);
        String appNameToCompare = appToBedeleted.getApplicationName();
        List<Application> applicationList = savedUser.getApplications();
        List<Application> listOfApplicationsAfterDeletion = new ArrayList<>();
        Iterator applicationIterator = applicationList.iterator();
        while (applicationIterator.hasNext()){
            Application appToBeComparedWith = (Application) applicationIterator.next();
            if (appToBeComparedWith.getApplicationName().equals(appNameToCompare)){

            }
            else{
                listOfApplicationsAfterDeletion.add(appToBeComparedWith);
            }
        }
        User updatedUser = new User(userObj.getUserName(),listOfApplicationsAfterDeletion);
        applicationRepositoryObj.save(updatedUser);
        return updatedUser;
    }
}