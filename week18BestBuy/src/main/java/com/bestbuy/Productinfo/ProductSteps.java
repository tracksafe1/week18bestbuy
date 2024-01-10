package com.bestbuy.Productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductSteps {
    static int id;

    @Step("Creating student with name : {0}, type: {1}, price: {2}, shipping: {3} , upc: {4}, description: {5}, manufacturer: {6}, model:{7},url:{8}, image:{9}")
    public ValidatableResponse createProduct(String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {
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


        return SerenityRest.given().log().all()

                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post()
                .then().log().all();
    }
    @Step("Getting the product information with id: {0}")
    public ValidatableResponse getProductInfoById(int id) {
        return SerenityRest.given().log().all()

                .pathParam("id",id)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Getting the product information with id: {0}")
    public ValidatableResponse getAllProductInfo() {
        return SerenityRest.given().log().all()

                .when()
                .get()
                .then().log().all();
    }

    @Step("Creating student with name : {0}, type: {1}, price: {2}, shipping: {3} , upc: {4}, description: {5}, manufacturer: {6}, model:{7},url:{8}, image:{9}")
    public ValidatableResponse updateProduct(int id, String name, String type, int price,
                                             int shipping, String upc, String description, String manufacturer, String model, String url, String image) {
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

        return SerenityRest.given().log().all()

                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(productPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Deleting product information with id: {0}")
    public ValidatableResponse deleteProduct(int id) {
        return SerenityRest.given().log().all()

                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }
}