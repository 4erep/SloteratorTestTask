package com.testslotegrator.api.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegPlayerCredentials {
    @Builder.Default
    @JsonProperty("grant_type")
    private String grant_type = "password";
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
