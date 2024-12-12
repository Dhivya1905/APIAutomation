package com.testingacademy.Integration;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class API_Testing_Integration_Scenario4 {

    private String token;
    private  String bookingId;
    private RequestSpecification requestSpecification;

    @BeforeMethod
    public void setup() {
        API_Testing_Integration_Scenario3 apiTestingIntegrationScenario3 = new API_Testing_Integration_Scenario3();
        apiTestingIntegrationScenario3.setup();
        apiTestingIntegrationScenario3.test_create_booking(); // Explicitly create booking
        bookingId = apiTestingIntegrationScenario3.getBookingId();
         System.out.println("BookingID from setup: " + bookingId);
         token = apiTestingIntegrationScenario3.getToken();
        System.out.println("Token from setup: " + token);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
    }

    @Description("Delete a created  booking  using BookingID ")
    @Test(priority = 3)
    public void delete_booking(){
        System.out.println("Booking ID from delete_booking: " + bookingId);

        requestSpecification.basePath("/booking/" + bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        Response response=requestSpecification.when().log().all().delete();
        response.then().log().all().statusCode(201);

    }
@Description("Attempt to update a deleted booking")
    @Test(priority =4,dependsOnMethods = "delete_booking")
    public void  update_deleted_booking() {

        String payload ="{\n" +
                "    \"firstname\": \"Vignesh\",\n" +
                "    \"lastname\": \"Kumar\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2025-01-01\",\n" +
                "        \"checkout\": \"2025-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

    requestSpecification.basePath("/booking/" + bookingId);
    requestSpecification.contentType("application/json");
    requestSpecification.accept("application/json");
    requestSpecification.body(payload).log().all();



    Response response = requestSpecification.when().log().all().put();
    response.then().log().all().statusCode(403);
}




}


