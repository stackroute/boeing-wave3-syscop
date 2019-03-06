//package com.stackroute.processorservice.service;
//
//import com.stackroute.processorservice.model.Application;
//import com.stackroute.processorservice.model.Services;
//import com.stackroute.processorservice.model.User;
//import com.stackroute.processorservice.repository.DataCollectorRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.internal.verification.VerificationModeFactory.times;
//
//public class AppRegDataServiceImplTest {
//
//    @Mock
//    DataCollectorRepository dataCollectorRepository;
//
//    @InjectMocks
//    AppRegDataServiceImpl appRegDataServiceImpl;
//
//    private User user = new User();
//    private Application application;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        Services service = new Services("crudj2e","J2E", 8000);
//        List<Services> serviceList = new ArrayList<>();
//        serviceList.add(service);
//        Date dateObj = new Date();
//        application = new Application("SysCop","Docker","172.13.44.55",dateObj,serviceList);
//        List<Application> applicationList = new ArrayList<>();
//        user = new User("Ramya",applicationList);
//    }
//
//    @Test
//    public void addApplicationTest()  {
//        when(dataCollectorRepository.save(user)).thenReturn(user);
//        User expectedApplication=appRegDataServiceImpl.saveUser(user);
//        assertEquals(user,expectedApplication);
//        verify(applicationRepository,times(1)).save(user);
//    }
//
//
//
//
//}