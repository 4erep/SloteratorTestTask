package com.testslotegrator.api.tests;

import com.testslotegrator.api.dataBuilders.PlayerDataBuilders;
import com.testslotegrator.api.core.BaseApi;
import com.testslotegrator.api.pojo.CreatedPlayer;
import com.testslotegrator.api.pojo.NewPlayer;
import com.testslotegrator.api.pojo.RegPlayerCredentials;
import com.testslotegrator.api.pojo.Token;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Tag("API")
public class ApiTests {

    PlayerDataBuilders newPlayer = new PlayerDataBuilders();

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
        NewPlayer dataToCreatePlayer = newPlayer.createPlayer();
        var authorizationToken = BaseApi.getGuestToken().as(Token.class);
        String token = authorizationToken.getAccessToken();
        var createdPlayer = BaseApi.registerPlayer(dataToCreatePlayer, token);

        assertStatusCode(createdPlayer.statusCode(), HttpStatus.SC_CREATED);
        assertThat(dataToCreatePlayer.getUsername(), equalTo(createdPlayer.as(CreatedPlayer.class).getUsername()));
    }

    @Test
    @Description("Проверка, что при логине пользователя приходит токен")
    @DisplayName("Авторизация пользователя и получения токена")
    public void loginWithRegisteredPlayerTest() {
        NewPlayer dataToCreatePlayer = newPlayer.createPlayer();
        var authorizationToken = BaseApi.getGuestToken().as(Token.class);
        String token = authorizationToken.getAccessToken();
        BaseApi.registerPlayer(dataToCreatePlayer, token);
        RegPlayerCredentials body = newPlayer.registeredPlayerCredentials(dataToCreatePlayer.getUsername(),
                dataToCreatePlayer.getPasswordChange());
        var registeredPlayerToken = BaseApi.getRegisteredPlayerToken(body);

        assertThat(registeredPlayerToken.as(Token.class).getAccessToken(), is(notNullValue()));
        assertStatusCode(registeredPlayerToken.statusCode(), HttpStatus.SC_OK);
    }

    @Test
    @Description("Проверка, что при запросе профиля пользователя приходит ответ, соответсвующий телу созданного пользователя")
    @DisplayName("Получения профиля пользователя")
    public void getCurrentPlayerProfileTest() {
        NewPlayer dataToCreatePlayer = newPlayer.createPlayer();
        var authorizationToken = BaseApi.getGuestToken().as(Token.class);
        String token = authorizationToken.getAccessToken();
        var newPlayerInfo = BaseApi.registerPlayer(dataToCreatePlayer, token).as(CreatedPlayer.class);
        RegPlayerCredentials body = newPlayer.registeredPlayerCredentials(dataToCreatePlayer.getUsername(),
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
        NewPlayer dataToCreatePlayer = newPlayer.createPlayer();
        var authorizationToken = BaseApi.getGuestToken().as(Token.class);
        String token = authorizationToken.getAccessToken();
        BaseApi.registerPlayer(dataToCreatePlayer, token).as(CreatedPlayer.class);
        RegPlayerCredentials body = newPlayer.registeredPlayerCredentials(dataToCreatePlayer.getUsername(),
                dataToCreatePlayer.getPasswordChange());
        var registeredPlayerToken = BaseApi.getRegisteredPlayerToken(body).as(Token.class);
        var currentPlayerProfile = BaseApi.getPlayerProfile(registeredPlayerToken.getAccessToken(),
                newPlayer.generateId());

        assertStatusCode(currentPlayerProfile.statusCode(), HttpStatus.SC_NOT_FOUND);
    }

}
