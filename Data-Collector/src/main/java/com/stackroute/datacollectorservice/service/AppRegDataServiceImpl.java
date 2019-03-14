package com.stackroute.datacollectorservice.service;

import com.stackroute.datacollectorservice.model.User;
import com.stackroute.datacollectorservice.repository.DataCollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppRegDataServiceImpl implements AppRegDataService {

    private DataCollectorRepository dataCollectorRepository;

    @Autowired
    public AppRegDataServiceImpl(DataCollectorRepository dataCollectorRepository) {
        this.dataCollectorRepository = dataCollectorRepository;
    }

    @Override
    public User saveUser(User user) {


        dataCollectorRepository.save(user);

        return user;

    }
}
