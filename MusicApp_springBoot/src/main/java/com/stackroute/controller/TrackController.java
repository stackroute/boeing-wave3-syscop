package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackDoesNotExistException;
import com.stackroute.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v3/")
public class TrackController {

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService musicService) {
        this.trackService = musicService;
    }

    @ApiOperation(value = "Saves a valid track" )
    @PostMapping(value = "track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {

        ResponseEntity responseEntity;

                Track track1= trackService.saveTrack(track) ;
                responseEntity = new ResponseEntity<String>("Successfully saved!", HttpStatus.CREATED);

        return responseEntity;
    }

    @ApiOperation(value = "Returns all the tracks saved")
    @GetMapping(value = "tracks")
    public ResponseEntity<?> getAllTracks() throws TrackDoesNotExistException {
        ResponseEntity responseEntity;


            responseEntity = new ResponseEntity<List<Track>>(trackService.displayTracks(), HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "Deletes a track of given id if it exists")
    @DeleteMapping(value = "track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable(value = "id") int id) throws TrackDoesNotExistException{
        ResponseEntity responseEntity;

                responseEntity = new ResponseEntity<Track>(trackService.removeTrack(id), HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "Updates the comment of a track")
    @PutMapping(value = "track")
    public ResponseEntity<?> updateComment(@RequestBody Track track) throws TrackDoesNotExistException{
        ResponseEntity responseEntity;

                Track music1= trackService.updateComment(track);
                responseEntity = new ResponseEntity<Track>(music1, HttpStatus.ACCEPTED);

        return responseEntity;
    }

    @ApiOperation(value = "Returns all the tracks of given name")
    @GetMapping(value = "track/{trackName}")
    public ResponseEntity<?> findTrackByTrackName(@PathVariable(value = "trackName") String trackName) throws TrackDoesNotExistException{
        ResponseEntity responseEntity;

            List<Track> trackList=trackService.displayTrackByName(trackName);
            responseEntity=new ResponseEntity<List<Track>>(trackList,HttpStatus.OK);

        return responseEntity;
    }
//
//    @GetMapping(value = "track/id/{trackId}")
//    public ResponseEntity<?> displayWithIdMoreThan(@PathVariable(value = "trackId") int trackId){
//        ResponseEntity<?> responseEntity;
//
//        try{
//            List<Track> trackList=trackService.displayTrackWithIdGreaterThan(trackId);
//            responseEntity=new ResponseEntity<List<Track>>(trackList,HttpStatus.OK);
//        }catch (TrackDoesNotExistException e){
//            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.GONE);
//        }
//        return responseEntity;
//
//    }
}
