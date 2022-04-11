package com.testslotegrator.ui.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:ui/common.properties"})
public interface UiCommonProperties extends Config {

    @Key("ui.base_uri")
    String UiBaseURI();

    @Key("ui.login")
    String UserLogin();

    @Key("ui.password")
    String UserPassword();

}