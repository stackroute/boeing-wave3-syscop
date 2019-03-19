package com.stackroute.appregistration.repository;

import com.stackroute.appregistration.domain.Application;
import com.stackroute.appregistration.domain.Service;
import com.stackroute.appregistration.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Test case for repository layer where connection is checked
 */
@RunWith(SpringRunner.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;
    private User user;

    @Before
    public void setUp() throws Exception{
        Service service = new Service("crudj2e","J2E", 8000);
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(service);
        Date dateObj = new Date();
        Application application = new Application("SysCop","Docker","172.13.44.55",dateObj,serviceList);
        List<Application> applicationList = new ArrayList<>();
        user = new User("Ramya",applicationList);
    }

    @Test
    public void testApplicationRegistrationSuccess() {
        applicationRepository.save(user);
        User object = applicationRepository.findById(user.getUserName()).get();
        assertEquals(user,object);
    }
}
