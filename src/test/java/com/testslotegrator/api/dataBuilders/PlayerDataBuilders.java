package com.testslotegrator.api.dataBuilders;

import com.testslotegrator.api.pojo.NewPlayer;
import com.testslotegrator.api.pojo.RegPlayerCredentials;
import com.testslotegrator.api.utils.FakerDataGenerator;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;


public class PlayerDataBuilders {

    FakerDataGenerator fakeData = new FakerDataGenerator();

    public NewPlayer createPlayer() {
        return NewPlayer.builder().
                username((fakeData).getUsername()).
                passwordChange(Base64Coder.encodeString(fakeData.getPassword())).
                passwordRepeat(Base64Coder.encodeString(fakeData.getPassword())).
                email(fakeData.getEmail()).
                name(fakeData.getName()).
                surname(fakeData.getSurname()).
                build();
    }

    public RegPlayerCredentials registeredPlayerCredentials(String name, String password) {
        return RegPlayerCredentials.builder().
                username(name).
                password(password).
                build();
    }

    public int generateId() {
       return fakeData.getId();
    }

}
