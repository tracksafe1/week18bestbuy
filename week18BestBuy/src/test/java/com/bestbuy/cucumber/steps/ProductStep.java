package com.bestbuy.cucumber.steps;

import com.bestbuy.Productinfo.ProductSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.collection.IsMapContaining.hasValue;

public class ProductStep {
    static int id;
    static  String name=null;
    static ValidatableResponse response;
    @Steps
    ProductSteps productSteps;
    @Given("^Best buy application is running$")
    public void bestBuyApplicationIsRunning() {
    }

    @When("^I create a new product by providing the information name \"([^\"]*)\" type \"([^\"]*)\" price \"([^\"]*)\" shipping \"([^\"]*)\" upc \"([^\"]*)\" description \"([^\"]*)\" manufacturer \"([^\"]*)\" model \"([^\"]*)\" url \"([^\"]*)\" image \"([^\"]*)\"$")
    public void iCreateANewProductByProvidingTheInformationNameTypePriceShippingUpcDescriptionManufacturerModelUrlImage(String _name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image)  {
name= TestUtils.getRandomValue()+_name;
response=productSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image);

        id = response.extract().path("id");

    }

    @And("^I verify that the product with name \"([^\"]*)\" is created$")
    public void iVerifyThatTheProductWithNameIsCreated(String _name) {
        response.statusCode(201);
        response = productSteps.getProductInfoById(id);
        HashMap<String,Object> value = response.extract().path("");
        id = (int) value.get("id");
        Assert.assertThat(value,hasValue(name));
    }

    @And("^I update product by providing the information name \"([^\"]*)\" type \"([^\"]*)\" price \"([^\"]*)\" shipping \"([^\"]*)\" upc \"([^\"]*)\" description \"([^\"]*)\" manufacturer \"([^\"]*)\" model \"([^\"]*)\" url \"([^\"]*)\" image \"([^\"]*)\"$")
    public void iUpdateProductByProvidingTheInformationNameTypePriceShippingUpcDescriptionManufacturerModelUrlImage(String _name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        name = name + "_Updated";
        response =productSteps.updateProduct(id,name, type, price,
                shipping, upc, description,manufacturer, model,url,image);
    }

    @And("^The product with name \"([^\"]*)\" is updated successfully$")
    public void theProductWithNameIsUpdatedSuccessfully(String _name) {
        response.statusCode(200);
        response =  productSteps.getProductInfoById(id).statusCode(200);
        HashMap<String,Object> value = response.extract().path("");
        Assert.assertThat(value,hasValue(name));
    }

    @And("^I delete the product that created with name \"([^\"]*)\"$")
    public void iDeleteTheProductThatCreatedWithName(String _name){
        productSteps.deleteProduct(id);
    }

    @Then("^The product deleted successfully from the application$")
    public void theProductDeletedSuccessfullyFromTheApplication() {
        response.statusCode(200);
        productSteps.getProductInfoById(id).statusCode(404);
    }
}
