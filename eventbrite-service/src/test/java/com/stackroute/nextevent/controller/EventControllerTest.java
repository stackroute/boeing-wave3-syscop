//package com.stackroute.nextevent.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.nextevent.exception.EventAlreadyExist;
//import com.stackroute.nextevent.exception.EventNotFound;
//import com.stackroute.nextevent.model.Category;
//import com.stackroute.nextevent.model.Event;
//import com.stackroute.nextevent.model.SubCategory;
//import com.stackroute.nextevent.model.Venu;
//import com.stackroute.nextevent.service.EventService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(EventController.class)
//public class EventControllerTest {
//    @MockBean
//    private EventService eventService;
//
//    private MockMvc mockMvc;
//
//    private Event event;
//
//    @InjectMocks
//    private EventController eventController;
//
//    private List<Event> eventList;
//
//
//    @Before
//    public void setUp() throws Exception {
//        eventList = new ArrayList<>();
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
//        Date dateFormat = new Date("1234/12/23 23:23:23");
//        Venu venu= new Venu("12","bangalore","bangalore",40);
//        SubCategory subCategory= new SubCategory("1","music1");
//        List<SubCategory> subCategoryList= new ArrayList<>();
//        subCategoryList.add(subCategory);
//        Category category = new Category("12","music",subCategoryList,18);
//        event = new Event( "123","123Efg","Event1","this is event1", dateFormat, dateFormat,dateFormat,venu,category,"imageurl");
//        eventList.add(event);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }
//
//    @Test
//    public void testSaveEvent() throws Exception {
//        when(eventService.saveEvent(any())).thenReturn(event);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/events")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonToString(event)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//    }
//    private static String jsonToString(final Object ob) throws JsonProcessingException {
//        String result;
//
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonContent = mapper.writeValueAsString(ob);
//            result = jsonContent;
//        } catch(JsonProcessingException e) {
//            result = "JSON processing error";
//        }
//
//        return result;
//    }
//
//
//}
