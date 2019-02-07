package com.stackroute.nextevent.repository;


import com.stackroute.nextevent.model.Category;
import com.stackroute.nextevent.model.Event;
import com.stackroute.nextevent.model.SubCategory;
import com.stackroute.nextevent.model.Venu;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@DataMongoTest
@SpringBootTest
public class EventRepositoryTestIt {

    @Test
    public void findByDescription() throws Exception {
        assertEquals("Teaspoon", "Teaspoon");
    }
}

