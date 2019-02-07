package com.stackroute.nextevent.repository;

import com.stackroute.nextevent.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event,String> {

    Event findByEventId(String id);

}

