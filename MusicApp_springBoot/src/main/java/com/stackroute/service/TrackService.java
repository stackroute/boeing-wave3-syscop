package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackDoesNotExistException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> displayTracks() throws TrackDoesNotExistException;

    public Track updateComment(Track track) throws TrackDoesNotExistException;

    public Track removeTrack(int id) throws TrackDoesNotExistException;

    public List<Track> displayTrackByName(String trackName) throws TrackDoesNotExistException;

//    public List<Track> displayTrackWithIdGreaterThan(int trackId) throws TrackDoesNotExistException;
}
