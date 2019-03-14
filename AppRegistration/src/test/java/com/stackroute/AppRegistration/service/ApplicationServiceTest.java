package com.stackroute.AppRegistration.service;

import com.stackroute.AppRegistration.domain.Application;
import com.stackroute.AppRegistration.domain.Service;
import com.stackroute.AppRegistration.domain.User;
import com.stackroute.AppRegistration.exceptions.ApplicationAlreadyExistException;
import com.stackroute.AppRegistration.exceptions.ApplicationDoesNotExistException;
import com.stackroute.AppRegistration.repository.ApplicationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
        * Test cases for service layer where we mock the repository layer
        * There are four tests. Each for each method in controller
        */
public class ApplicationServiceTest {

    @Mock
    ApplicationRepository applicationRepository;

    @InjectMocks
    ApplicationServiceImpl applicationServiceImpl;

    private User user = new User();
    private Application application;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Service service = new Service("crudj2e","J2E", 8000);
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(service);
        Date dateObj = new Date();
        application = new Application("SysCop","Docker","172.13.44.55",dateObj,serviceList);
        List<Application> applicationList = new ArrayList<>();
        user = new User("Ramya",applicationList);
    }

    @Test
    public void addApplicationTest() throws ApplicationAlreadyExistException {
        when(applicationRepository.save(user)).thenReturn(user);
        User expectedApplication=applicationServiceImpl.addApplication(user);
        assertEquals(user,expectedApplication);
        verify(applicationRepository,times(1)).save(user);
    }

    @Test
    public void getAllApplicationsTest() throws ApplicationDoesNotExistException{
        when(applicationRepository.existsById("Ramya")).thenReturn(true);
        when(applicationRepository.findById("Ramya")).thenReturn(Optional.of(user));
        User expectedApplication=applicationServiceImpl.getAllApplications("Ramya");
        assertEquals(user, expectedApplication);
        verify(applicationRepository,times(1)).findById("Ramya");
        verify(applicationRepository,times(1)).existsById("Ramya");
    }

    @Test
    public void updateApplications() throws ApplicationDoesNotExistException{
        when(applicationRepository.findById("Ramya")).thenReturn(Optional.of(user));
        when(applicationRepository.save(user)).thenReturn(user);
        User expectedApplication = applicationServiceImpl.updateApplications(user);
        assertEquals(user,expectedApplication);
        verify(applicationRepository,times(1)).findById("Ramya");
        verify(applicationRepository,times(1)).save(user);
    }

    @Test
    public void deleteApplications() throws ApplicationDoesNotExistException{
        when(applicationRepository.findById("Ramya")).thenReturn(Optional.of(user));
        when(applicationRepository.save(user)).thenReturn(user);
        User expectedApplication = applicationServiceImpl.deleteApplication(user);
        assertEquals(user,expectedApplication);
        verify(applicationRepository,times(1)).findById("Ramya");
        verify(applicationRepository,times(1)).save(user);
    }


    
}