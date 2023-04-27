package com.jazztech.cadastrar.clientes;

import static io.restassured.RestAssured.*;

import com.jazztech.apicadastro.Application;
import com.jazztech.apicadastro.applicationservice.domain.entity.Clients;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import io.restassured.http.ContentType;
import io.restassured.RestAssured.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.equalTo;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.ALWAYS, classes = Application.class)
@AutoConfigureMockMvc
public class ApiCadastroTeste {
    @Autowired
    MockMvc mockMvc;
    private static Clients clients;
    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void validateClientById() {
        given()
                .when()
                .get("http://localhost:8080/clients/id?id=1")
                .then()
                .log().all();
    }
}
