package com.testingacademy.Integration;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Integration_Scenario2 {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String bookingId;


    @BeforeMethod public void setup(){
        requestSpecification=RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
    }


    @Description("Verify the Bookingid is created Successfully")
    @Test(priority = 1)
    public void test_create_booking(){

        String payload= "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload).log().all();

        response=requestSpecification.when().log().all().post();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        bookingId= response.jsonPath().getString("bookingid");
        System.out.println(bookingId);
    }

    @Description("Verify the created _bookingID is fetched using get method")
    @Test(priority = 2,dependsOnMethods ="test_create_booking")
    public void get_created_booking(){
        requestSpecification.basePath("/booking/"+bookingId);

        response=requestSpecification.when().log().all().get();

        validatableResponse.statusCode(200);


    }
}
