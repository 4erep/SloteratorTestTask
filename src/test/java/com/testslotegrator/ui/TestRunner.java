package com.testslotegrator.ui;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:ui/features/LoginAndSort.feature"},
        glue = {"com.testslotegrator.ui.steps"})
public class TestRunner {

}
