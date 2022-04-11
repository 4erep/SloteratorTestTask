package com.testslotegrator.api.core;

import com.testslotegrator.api.config.ApiCommonProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static com.testslotegrator.api.config.Route.BASE_PATH;

public class SpecBuilder {
   public static ApiCommonProperties apiCommonProperties = ConfigFactory.create(ApiCommonProperties.class, System.getProperties());

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri(apiCommonProperties.apiBaseURL()).
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                log((LogDetail.ALL)).
                build();
    }

}
