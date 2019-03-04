package com.stackroute.userservice;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * Integration test with WebTest client which provides an asynchronous and reactive http client called Web_client.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserserviceApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    UserRepository userRepository;

    /**
     * Test case for reactive-@PostMapping.
     */

    @Test
    public void testCreateUser() {
        User user = new User("This is a Test User");

        webTestClient.post().uri("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.username").isEqualTo("This is a Test User");
    }

    /**
     * Test cases for Reactive-@GetMapping.
     */

    @Test
    public void testGetAllUser() {
        webTestClient.get().uri("/api/v1/users")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(User.class);
    }

    /**
     * Test cases for Reactive-@DeleteMapping.
     */
    @Test
    public void testDeleteUser() {
        User user = userRepository.save(new User("To be deleted")).block();

        webTestClient.delete()
                .uri("/api/v1/user/{id}", Collections.singletonMap("id", user.getId()))
                .exchange()
                .expectStatus().isOk();
    }

}
