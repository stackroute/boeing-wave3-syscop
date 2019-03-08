package com.stackroute.threshholdservice.repository;

import com.stackroute.threshholdservice.model.MetricsThreshold;
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
public class ThresholdRepositoryTest {

    @Autowired
    private ThresholdRepository thresholdRepository;
    private MetricsThreshold metricsThreshold;

    @Before
    public void setUp() throws Exception{

        metricsThreshold = new MetricsThreshold("avinash","lololreg",1.23f, 1.09f);
    }

    @Test
    public void testThresholdSaveThresholdSuccess() {
        thresholdRepository.save(metricsThreshold);
        MetricsThreshold object = thresholdRepository.findById(metricsThreshold.getUserName()).get();
        assertEquals(metricsThreshold.toString(),object.toString());
    }




}