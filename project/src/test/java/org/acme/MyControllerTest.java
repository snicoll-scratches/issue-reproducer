package org.acme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyControllerTest {

    @LocalServerPort
    String port;

    @Test
    void test() {
        WebTestClient client = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port + "/test")
                .build();

        String response = client.get()
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertEquals("MyAnnotatedClass", response);
    }
}