package com.stackroute.threshholdservice.service;

import com.stackroute.threshholdservice.model.MetricsThreshold;
import com.stackroute.threshholdservice.repository.ThresholdRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ThresholdServiceImplTest {

    @Mock
    ThresholdRepository thresholdRepository;

    @InjectMocks
    ThresholdServiceImpl thresholdServiceImpl;

    private MetricsThreshold metricsThreshold = new MetricsThreshold();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
//        user = new User("Ramya",applicationList);
        metricsThreshold = new MetricsThreshold("avinash","lololreg",1.24f,1.09f);
    }


    @Test
    public void addThresholdTest() {
        when(thresholdRepository.save(metricsThreshold)).thenReturn(metricsThreshold);
        MetricsThreshold expectedThreshold = thresholdServiceImpl.saveMetricsThreshold(metricsThreshold);
        assertEquals(metricsThreshold,expectedThreshold);
        verify(thresholdRepository,times(1)).save(metricsThreshold);
    }

    @Test
    public void getThresholdTest() {

        when(thresholdRepository.getMetricThresholdObject("avinash","lololreg")).thenReturn(metricsThreshold);
        MetricsThreshold expectedThreshold = thresholdServiceImpl.getMetricThresholdObj("avinash","lololreg");
        assertEquals(metricsThreshold,expectedThreshold);
        verify(thresholdRepository,times(1)).getMetricThresholdObject("avinash","lololreg");

    }



}