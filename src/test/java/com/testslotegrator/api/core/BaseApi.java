package com.testslotegrator.api.core;

import com.testslotegrator.api.config.ApiCommonProperties;
import com.testslotegrator.api.pojo.NewPlayer;
import com.testslotegrator.api.pojo.RegPlayerCredentials;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;

import static com.testslotegrator.api.config.Route.PLAYERS;
import static com.testslotegrator.api.config.Route.TOKEN;
import static com.testslotegrator.api.core.SpecBuilder.getRequestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BaseApi {

    public static ApiCommonProperties apiCommonProperties = ConfigFactory.create(ApiCommonProperties.class, System.getProperties());

    public static Response getGuestToken() {
        HashMap<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        body.put("scope", "guest:default");

        return given(getRequestSpec()).
                auth().preemptive().
                basic(apiCommonProperties.guestLogin(), apiCommonProperties.guestPassword()).
                body(body).
                when().post(TOKEN).
                then().
                assertThat().body(matchesJsonSchemaInClasspath("api/schemas/Token.json")).
                log().all().
                extract().
                response();
    }

    public static Response getRegisteredPlayerToken(RegPlayerCredentials regPlayerCredentials) {
        return given(getRequestSpec()).
                auth().preemptive().
                basic(apiCommonProperties.guestLogin(), apiCommonProperties.guestPassword()).
                body(regPlayerCredentials).
                when().post(TOKEN).
                then().
                assertThat().body(matchesJsonSchemaInClasspath("api/schemas/Token.json")).
                log().all().
                extract().
                response();
    }


    public static Response registerPlayer(NewPlayer newPlayer, String token) {
        return given(getRequestSpec()).
                auth().
                oauth2(token).
                body(newPlayer).
                when().post(PLAYERS).
                then().
                assertThat().body(matchesJsonSchemaInClasspath("api/schemas/UserCredentials.json")).
                log().all().
                extract().
                response();
    }

    public static Response getPlayerProfile(String token, int id) {
        return given(getRequestSpec()).
                auth().
                oauth2(token).
                when().get(PLAYERS + "/" + id).
                then().
                log().all().
                extract().response();
    }

}
