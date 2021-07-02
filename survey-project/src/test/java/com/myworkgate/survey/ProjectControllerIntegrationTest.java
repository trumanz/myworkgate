package com.myworkgate.survey;


import com.myworkgate.survey.model.Project;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);

        restTemplate.setRequestFactory(requestFactory);

        List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptorList);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));

    }

    @BeforeEach
    public void stepUp() {
        baseUrl = baseUrl.concat(":").concat(port+"");
    }

    @AfterEach
    @Sql(statements = "DELETE * from project")
    public void cleanUp(){

    }

    @Test
    @Sql(statements = "INSERT INTO project (id, name) VALUES (1, 'x'), (2, 'y')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void queryExistProject() {
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

    //C--R
    @Test
    public void create_Retrieve_Project() {
        var proj = new Project();
        proj.setName("Jan");
        ResponseEntity<Project> response = restTemplate.postForEntity(
                baseUrl.concat("/projects"), proj,Project.class);

        assertEquals( HttpStatus.CREATED, response.getStatusCode());
        assertEquals( "Jan", response.getBody().getName());

        ResponseEntity<Project> response_get = restTemplate.getForEntity(
                baseUrl.concat("/projects/{id}"), Project.class, response.getBody().getId());

        assertEquals( HttpStatus.OK, response_get.getStatusCode());
        assertEquals( "Jan", response_get.getBody().getName());
        assertEquals( response.getBody().getId(), response_get.getBody().getId());

    }

    //Update--Retrieve
    @Test
    @Sql(statements = "INSERT INTO project (id, name) VALUES (5, 'Java')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void updateProject_Retrieve() {
        var proj = new Project();
        proj.setId(5L);
        proj.setName("GoLang");

        restTemplate.patchForObject( baseUrl.concat("/projects/{id}"),  proj,
                 Project.class,5);

        ResponseEntity<Project> response_get = restTemplate.getForEntity(
                baseUrl.concat("/projects/{id}"), Project.class, 5);

        assertEquals( HttpStatus.OK, response_get.getStatusCode());
        assertEquals( "GoLang", response_get.getBody().getName());
        assertEquals( 5L, response_get.getBody().getId());

    }

    //DELETE
    @Test
    @Sql(statements = "INSERT INTO project (id, name) VALUES (7, 'C++')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deleteObject_ShouldRetrieveResponseNotFound() {
        var proj = new Project();
        proj.setId(5L);
        proj.setName("GoLang");

        restTemplate.delete( baseUrl.concat("/projects/{id}"),7);


        HttpStatus status = HttpStatus.OK;
        try {
            ResponseEntity<Project> response_get = restTemplate.getForEntity(
                    baseUrl.concat("/projects/{id}"), Project.class, 7);
        } catch(HttpClientErrorException ex) {
            status = ex.getStatusCode();
            if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
        }

        assertEquals( HttpStatus.NOT_FOUND, status);

    }



}
