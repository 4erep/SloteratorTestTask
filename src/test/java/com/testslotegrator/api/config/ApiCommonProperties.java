package com.testslotegrator.api.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:api/common.properties"})
public interface ApiCommonProperties extends Config {
    @Key("api.base_url")
    String apiBaseURL();

    @Key("guest_login")
    String guestLogin();

    @Key("guest_password")
    String guestPassword();
}
