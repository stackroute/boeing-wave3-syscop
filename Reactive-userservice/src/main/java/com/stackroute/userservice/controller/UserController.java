package com.stackroute.userservice.controller;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *Created a reactive-controller class containing get,post and delete mapping.
 *Reactive-@GetMapping to get all the registered users.
 *Reactive-@PostMapping to add new users.
 *Reactive-@DeleteMapping to delete the registered users.
 * Reactors:
 * Flux and Mono Reactor types are used to implement the publisher interface provided by reactive streams.
 */
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * In Reactive-@GetMapping, Reactor method Flux is used which represents stream of 0 to N elements.
     * @return Flux in the form of publisher.
     */
    @GetMapping("users")
    public Flux<User> getAllStudents() {
        return userRepository.findAll();
    }

    /**
     * In Reactive-@PostMapping, Reactor method Mono is used which represents stream of 0 or 1 element.
     * @param user
     * @return Mono in the form of publisher.
     */
    @PostMapping("register")
    public Mono<User> createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * Reactive-@DeleteMapping implements the Mono Reactor.
     * @param userId
     * @return Mono in the form of publisher.
     */
    @DeleteMapping("/user/{id}")
    public Mono<ResponseEntity<Void>> deleteUsers(@PathVariable(value = "id") String userId) {

        return userRepository.findById(userId)
                .flatMap(existingUser ->
                        userRepository.delete(existingUser)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
