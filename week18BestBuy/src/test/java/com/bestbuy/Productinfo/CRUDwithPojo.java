package com.bestbuy.Productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBaseBestBuyApi;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;

import net.thucydides.core.annotations.Title;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;


import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class CRUDwithPojo extends TestBaseBestBuyApi {

    static String name = "Battery" + TestUtils.getRandomValue();
    static String type = "AA" + TestUtils.getRandomValue();
    static int price = 100 + TestUtils.getRandomNumber();
    static Integer shipping = 5 + TestUtils.getRandomNumber();
    static String upc = "Company1" + TestUtils.getRandomValue();
    static String description = "MR22" + TestUtils.getRandomValue();
    static String manufacturer = "string" + TestUtils.getRandomValue();
    static String model = "string" + TestUtils.getRandomValue();
    static String url = "string" + TestUtils.getRandomValue();
    ;
    static String image = "string" + TestUtils.getRandomValue();
    ;
    static int productId;
    static ValidatableResponse response;





    @Test
    public void test001() {
        given()
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);
    }

    @Title("This will create a new student")
    @Test

    public void test002() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        Response response=given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post(EndPoints.CREATE_PRODUCT);
        productId= response.then().log().all().extract().path("id");
        System.out.println(productId);

    }



    @Title("Update the user and verify the updated information")
    @Test
    public void test004() {
        name = name + "updated";
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);

        SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", productId)
                .body(productPojo)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all().statusCode(200);

    }

    @Title("Delete the product and verify if the student is deleted")
    @Test
    public void test005() {
        SerenityRest.given()
                .pathParam("id", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then()
                .statusCode(200);
        given().log().all()
                .pathParam("id", productId)
                .when()
                .get(EndPoints.DELETE_PRODUCT_BY_ID)
                .then()
                .statusCode(404);

    }

}





