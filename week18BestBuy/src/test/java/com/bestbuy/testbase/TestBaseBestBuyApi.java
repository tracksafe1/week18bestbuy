package com.bestbuy.testbase;


import com.bestbuy.constants.Path;
import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by bhavesh
 */
public class TestBaseBestBuyApi {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("bestBuy.BaseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("bestBuy.Port"));
      // RestAssured.basePath = Path.PRODUCT;
       // RestAssured.basePath = Path.STORE;

    }

}
