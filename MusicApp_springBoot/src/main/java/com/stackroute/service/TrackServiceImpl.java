package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackDoesNotExistException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        Track savedTrack=null;
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("Track already exists");
        }
            savedTrack = trackRepository.save(track);
        if(savedTrack==null){
            throw new TrackAlreadyExistsException("Track already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> displayTracks() throws TrackDoesNotExistException {
        List<Track> trackList= trackRepository.findAll();
        if (trackList.isEmpty()){
            throw new TrackDoesNotExistException("Your TrackList is Empty");
        }
        return trackList;
    }

    @Override
    public Track updateComment(Track track) throws TrackDoesNotExistException {
       Track returnTrackObj=null;
        if (trackRepository.existsById(track.getTrackId())){
            returnTrackObj= trackRepository.save(track);
        }else{
            throw new TrackDoesNotExistException("Track does not exist.");
        }
        return returnTrackObj;
    }

    @Override
    public Track removeTrack(int id) throws TrackDoesNotExistException {
        boolean flag=false;
        Track returnTrack;
        if(trackRepository.existsById(id)){
            returnTrack=trackRepository.findById(id).get();
            trackRepository.deleteById(id);
        }else{
            throw new TrackDoesNotExistException("Track does not exist.");
        }
        return returnTrack;
    }

    @Override
    public List<Track> displayTrackByName(String trackName) throws TrackDoesNotExistException {
        List<Track> trackList=trackRepository.findByTrackName(trackName);
        if (trackList.isEmpty()){
            throw new TrackDoesNotExistException("Track with \""+ trackName+"\" Track-Name, does not exist");
        }
        return trackList;
    }
//
//    @Override
//    public List<Track> displayTrackWithIdGreaterThan(int trackId) throws TrackDoesNotExistException {
//        List<Track> trackList=trackRepository.findTrackWithCondition(trackId);
//        if (trackList.isEmpty()){
//            throw new TrackDoesNotExistException("Track/s with trackId greater than "+trackId+" does not exist");
//        }
//        return trackList;
//    }
}
