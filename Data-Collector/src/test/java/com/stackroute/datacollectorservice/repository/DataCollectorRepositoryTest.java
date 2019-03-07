package com.stackroute.datacollectorservice.repository;

import com.stackroute.datacollectorservice.model.Application;
import com.stackroute.datacollectorservice.model.Services;
import com.stackroute.datacollectorservice.model.User;
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

@RunWith(SpringRunner.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)

public class DataCollectorRepositoryTest {
    @Autowired
    private DataCollectorRepository dataCollectorRepository;
    private User user;

    @Before
    public void setUp() throws Exception{
        Services service = new Services("crudj2e","J2E", 8000);
        List<Services> serviceList = new ArrayList<>();
        serviceList.add(service);
        Date dateObj = new Date();
        Application application = new Application("SysCop","Docker","172.13.44.55",dateObj,serviceList);
        List<Application> applicationList = new ArrayList<>();
        user = new User("Ramya",applicationList);
    }

    @Test
    public void testDataCollectorSaveUserSuccess() {
        dataCollectorRepository.save(user);
        User object = dataCollectorRepository.findById(user.getUserName()).get();
        assertEquals(user,object);
    }


}