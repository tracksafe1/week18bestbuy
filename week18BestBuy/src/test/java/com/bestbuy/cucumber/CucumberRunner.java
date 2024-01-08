package com.bestbuy.cucumber;

import com.bestbuy.testbase.TestBaseBestBuyApi;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;



@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/feature",
        tags = "@test")// tags are optional, can run from runner class


public class CucumberRunner extends TestBaseBestBuyApi {

}
