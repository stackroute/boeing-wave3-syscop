package com.stackroute.AppRegistration.service;

import com.stackroute.AppRegistration.repository.ApplicationRepository;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class ApplicationServiceTest {

    @Mock
    ApplicationRepository applicationRepository;

    @InjectMocks
    ApplicationServiceImpl applicationServiceImpl;

    @Before
    public void setUp() throws Exception {

    }
}