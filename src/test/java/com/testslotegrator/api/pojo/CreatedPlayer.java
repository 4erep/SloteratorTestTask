package com.testslotegrator.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class CreatedPlayer {
    @JsonProperty("id")
    private int id;
    @JsonProperty("country_id")
    private Object countryId;
    @JsonProperty("timezone_id")
    private Object timezoneId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("gender")
    private Object gender;
    @JsonProperty("phone_number")
    private Object phoneNumber;
    @JsonProperty("birthdate")
    private Object birthdate;
    @JsonProperty("bonuses_allowed")
    private boolean bonusesAllowed;
    @JsonProperty("is_verified")
    private boolean isVerified;
}
