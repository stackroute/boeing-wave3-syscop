package com.stackroute.processorservice.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stackroute.processorservice.adapter.UnixEpochDateTypeAdapter;
import com.stackroute.processorservice.model.User;
import com.stackroute.processorservice.repository.DataCollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AppRegDataServiceImpl implements AppRegDataService {

    private DataCollectorRepository dataCollectorRepository;

    @Autowired
    public AppRegDataServiceImpl(DataCollectorRepository dataCollectorRepository) {
        this.dataCollectorRepository = dataCollectorRepository;
    }

    @Override
    public User saveUser(String message) {

        UnixEpochDateTypeAdapter unixEpochDateTypeAdapter = new UnixEpochDateTypeAdapter();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, unixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter())
                .create();

        User user = gson.fromJson(message, User.class);
        System.out.println(user.toString());
        System.out.println("Saving......");
        dataCollectorRepository.save(user);
        System.out.println("Saved......");

        return user;

    }
}
