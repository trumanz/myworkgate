package com.myworkgate.survey;


import com.myworkgate.survey.model.Project;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//https://www.vincenzoracca.com/en/blog/framework/spring/integration-test/

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerIntegrationTest {

    @LocalServerPort
    private int port;
    private String  baseUrl = "http://localhost";
    private static RestTemplate restTemplate = null;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void stepUp() {
        baseUrl = baseUrl.concat(":").concat(port+"");
    }

    @Test
    @Sql(statements = "INSERT INTO project (id, name) VALUES (1, 'x'), (2, 'y')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void crud() {
        log.warn(":" + restTemplate);
        ResponseEntity<Project[]> response =
                restTemplate.getForEntity(baseUrl.concat("/projects"), Project[].class);
        log.info("response: " + response);
        Project[] projs = response.getBody();
        log.info("projs: " + Arrays.toString(projs));
        log.info("projs: " + projs.length );

        assertAll(
                ()->assertEquals(2,projs.length),
                ()->assertEquals("x",projs[0].getName()),
                ()->assertEquals("y",projs[1].getName())

        );

    }

}
