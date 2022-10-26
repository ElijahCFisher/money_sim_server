package com.pasiv.money_sim_server;

import com.pasiv.money_sim_server.models.Scenario;
import com.pasiv.money_sim_server.models.Source;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpMethod.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private Controller controller;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void scenarios() {
        var ret = new Scenario[]{new Scenario(), new Scenario()};
        var headers = new HttpHeaders();
        var httpEntity = new HttpEntity<>(ret, headers);

        var response = this.restTemplate.exchange("http://localhost:"+port+"/scenarios/", GET, httpEntity, Scenario[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertArrayEquals(response.getBody(), ret);
    }

    @Test
    void addSource() {
        var ret = new Source();
        var headers = new HttpHeaders();
        var httpEntity = new HttpEntity<>(ret, headers);

        ResponseEntity<Object> response = this.restTemplate.exchange("http://localhost:"+port+"/0/source/", POST, httpEntity, Object.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void editSource() {
        var ret = new Source();
        var headers = new HttpHeaders();
        var httpEntity = new HttpEntity<>(ret, headers);

        ResponseEntity<Object> response = this.restTemplate.exchange("http://localhost:"+port+"/0/source/"+ret.getId()+"/", PUT, httpEntity, Object.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void deleteSource() {
        var ret = new Source();

        ResponseEntity<Object> response = this.restTemplate.exchange("http://localhost:"+port+"/0/source/"+ret.getId()+"/", DELETE, null, Object.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}