package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//Approach-One using Application Listener
@Configuration
@PropertySource("classpath:seed.properties")
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

//Approach-Two using CommandLine Runner
//public class ApplicationStartup implements CommandLineRunner{

    @Value("${1trackId}")
    private int trackId1;
    @Value("${1trackName}")
    private String trackName1;
    @Value("${1trackComment}")
    private String trackComment1;
    @Value("${2trackId}")
    private int trackId2;
    @Value("${2trackName}")
    private String trackName2;
    @Value("${2trackComment}")
    private String trackComment2;



    @Autowired
    private TrackRepository trackRepository;

//Method to be over-ridden for applicationListener
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent applicationReadyEvent) {
        seedData();
    }

//Method to be over-ridden for command line runner
//    @Override
//    public void run(String... args) throws Exception {
//        seedData();
//    }

    private void seedData() {
        trackRepository.save(new Track(trackId1,trackName1,trackComment1));
        trackRepository.save(new Track(trackId2,trackName2,trackComment2));
    }

}


