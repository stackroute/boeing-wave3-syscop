package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TrackRepository extends MongoRepository<Track,Integer> {

    List<Track> findByTrackName(String trackName);


//    @Query(value = "select t from Track t where t.trackId>?1")
//    List<Track> findTrackWithCondition(int trackId);


//    Same query can also be written in this way where we use named parameters. We have multiple ways where
//    we can define our custom queries and pass parameters to them.
//
//    @Query(value = "select t from Track t where t.trackId>:trackId")
//    List<Track> findTrackWithCondition(@Param(value = "trackId") int trackId);


}
