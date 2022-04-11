package com.testslotegrator.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Builder
@Data
public class NewPlayer {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password_change")
	private String passwordChange;
	@JsonProperty("password_repeat")
	private String passwordRepeat;
	@JsonProperty("email")
	private String email;
	@JsonProperty("name")
	private String name;
	@JsonProperty("surname")
	private String surname;
	@JsonProperty("currency_code")
	private String currencyCode;
}