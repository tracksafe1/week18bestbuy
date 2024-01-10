package com.bestbuy.Productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import com.bestbuy.model.StorePojo.Services;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


    public class StoreSteps extends TestUtils {
        @Step("Creating store with name : {0}, type: {1}, address: {2}, address2: {3} , city: {4}, state: {5}, zip: {6}, lat:{7},lng:{8}, hours:{9}")
        public static ValidatableResponse createStore(String name, String type, String address,
                                                      String address2, String city, String state, String zip, int lat, int lng, String hours, Services services) {
            StorePojo storePojo = new StorePojo();
            storePojo.setName(name);
            storePojo.setType(type);
            storePojo.setAddress(address);
            storePojo.setAddress2(address2);
            storePojo.setCity(city);
            storePojo.setState(state);
            storePojo.setZip(zip);
            storePojo.setLat(lat);
            storePojo.setLng(lng);
            storePojo.setHours(hours);
            Services service = new StorePojo.Services();
            service.setServices(services);

            return SerenityRest.given().log().all()
                    .contentType(ContentType.JSON)
                    .body(storePojo)
                    .when()
                    .post()
                    .then().log().all();
        }
        @Step("Getting the store information with id: {0}")
        public static ValidatableResponse getStoreInfoById(int id) {
            return SerenityRest.given().log().all()

                    .pathParam("id", id)
                    .when()
                    .get(EndPoints.GET_STORE_BY_ID)
                    .then().log().all();
        }
        @Step("Getting  all the store information ")
        public  ValidatableResponse getAllStoreInfo(){
            return SerenityRest.given().log().all()

                    .when()
                    .get()
                    .then().log().all();
        }
        @Step("updating store with name : {0}, type: {1}, address: {2}, address2: {3} , city: {4}, state: {5}, zip: {6}, lat:{7},lng:{8}, hours:{9}")
        public static ValidatableResponse updateStore(int id, String name, String type, String address,
                                                      String address2, String city, String state, String zip, int lat, int lng, String hours, StorePojo.Services services) {
            StorePojo storePojo = new StorePojo();
            storePojo.setName(name);
            storePojo.setType(type);
            storePojo.setAddress(address);
            storePojo.setAddress2(address2);
            storePojo.setCity(city);
            storePojo.setState(state);
            storePojo.setZip(zip);
            storePojo.setLat(lat);
            storePojo.setLng(lng);
            storePojo.setHours(hours);
            StorePojo.Services service = new StorePojo.Services();
            service.setServices(services);
            return SerenityRest.given().log().all()
                    .contentType(ContentType.JSON)
                    .pathParam("id", id)
                    .body(storePojo)
                    .when()
                    .put(EndPoints.UPDATE_STORE_BY_ID)
                    .then().log().all();
        }
        @Step("Deleting store information with id: {0}")
        public static ValidatableResponse deleteStore(int id){
            return SerenityRest.given().log().all()

                    .pathParam("id", id)
                    .when()
                    .delete(EndPoints.DELETE_STORE_BY_ID)
                    .then();
        }
}
