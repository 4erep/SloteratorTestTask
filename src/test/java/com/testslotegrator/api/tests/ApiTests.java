package com.testslotegrator.api.tests;

import com.testslotegrator.api.core.BaseApi;
import com.testslotegrator.api.dataBuilders.PlayerDataBuilders;
import com.testslotegrator.api.pojo.CreatedPlayer;
import com.testslotegrator.api.pojo.Token;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Tag("API")
public class ApiTests {

    PlayerDataBuilders newPlayer = new PlayerDataBuilders();
    private String accessToken;


    public void getAccessToken() {
        accessToken = BaseApi.getGuestToken().as(Token.class).getAccessToken();

    }

    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    @Test
    @Description("Проверка, что при запросе токена гостевого пользователя приходит токен")
    @DisplayName("Получение токена гостевого пользователя")
    public void getGuestTokenTest() {
        var response = BaseApi.getGuestToken();

        assertThat(response.as(Token.class).getAccessToken(), is(notNullValue()));
        assertStatusCode(response.statusCode(), HttpStatus.SC_OK);
    }

    @Test
    @Description("Проверка, что создании пользователя приходит json с его именем")
    @DisplayName("Регистрация пользователя")
    public void registerPlayerTest() {
        var dataToCreatePlayer = newPlayer.createPlayer();
        getAccessToken();
        var createdPlayer = BaseApi.registerPlayer(dataToCreatePlayer, accessToken);

        assertThat(dataToCreatePlayer.getUsername(), equalTo(createdPlayer.as(CreatedPlayer.class).getUsername()));
        assertStatusCode(createdPlayer.statusCode(), HttpStatus.SC_CREATED);
    }

    @Test
    @Description("Проверка, что при логине пользователя приходит токен")
    @DisplayName("Авторизация пользователя и получения токена")
    public void loginWithRegisteredPlayerTest() {
        var dataToCreatePlayer = newPlayer.createPlayer();
        getAccessToken();
        BaseApi.registerPlayer(dataToCreatePlayer, accessToken);
        var body = newPlayer.registeredPlayerCredentials(dataToCreatePlayer.getUsername(),
                dataToCreatePlayer.getPasswordChange());
        var registeredPlayerToken = BaseApi.getRegisteredPlayerToken(body);

        assertThat(registeredPlayerToken.as(Token.class).getAccessToken(), is(notNullValue()));
        assertStatusCode(registeredPlayerToken.statusCode(), HttpStatus.SC_OK);
    }

    @Test
    @Description("Проверка, что при запросе профиля пользователя приходит ответ, соответсвующий телу созданного пользователя")
    @DisplayName("Получения профиля пользователя")
    public void getCurrentPlayerProfileTest() {
        var dataToCreatePlayer = newPlayer.createPlayer();
        getAccessToken();
        var newPlayerInfo = BaseApi.registerPlayer(dataToCreatePlayer, accessToken).as(CreatedPlayer.class);
        var body = newPlayer.registeredPlayerCredentials(dataToCreatePlayer.getUsername(),
                dataToCreatePlayer.getPasswordChange());
        var registeredPlayerToken = BaseApi.getRegisteredPlayerToken(body).as(Token.class);
        var currentPlayerProfile = BaseApi.getPlayerProfile(registeredPlayerToken.getAccessToken(),
                newPlayerInfo.getId()).as(CreatedPlayer.class);

        assertThat(newPlayerInfo, equalTo(currentPlayerProfile));
    }

    @Test
    @Description("Проверка, что при запросе профиля пользователя с другим ID приходит ошибка 404")
    @DisplayName("Получения профиля пользователя с другим ID")
    public void getWrongPlayerProfileTest() {
        var dataToCreatePlayer = newPlayer.createPlayer();
        getAccessToken();
        BaseApi.registerPlayer(dataToCreatePlayer, accessToken).as(CreatedPlayer.class);
        var body = newPlayer.registeredPlayerCredentials(dataToCreatePlayer.getUsername(),
                dataToCreatePlayer.getPasswordChange());
        var registeredPlayerToken = BaseApi.getRegisteredPlayerToken(body).as(Token.class);
        var currentPlayerProfile = BaseApi.getPlayerProfile(registeredPlayerToken.getAccessToken(),
                newPlayer.generateId());

        assertStatusCode(currentPlayerProfile.statusCode(), HttpStatus.SC_NOT_FOUND);
        currentPlayerProfile.then().body(matchesJsonSchemaInClasspath("api/schemas/Error.json"));
    }

}
