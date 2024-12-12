package com.testingacademy.Integration;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.requestSpecification;

public class API_Testing_Integration_Scenario3 {

    private API_Testing_Integration_Scenario2 apiTestingIntegrationScenario2;
    protected String bookingId;
    protected String token;
    private RequestSpecification requestSpecification;

    @BeforeMethod
public void setup() {
        apiTestingIntegrationScenario2 = new API_Testing_Integration_Scenario2();
        token= apiTestingIntegrationScenario2.authenticate();
        System.out.println("Token from setup: " + token);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");

    }


        @Description("Create a booking and retrieve  the bookinga id")
        @Test(priority = 1)

        public void test_create_booking() {

         bookingId=apiTestingIntegrationScenario2.create_booking();
         System.out.println("BookingID from test_create_booking:" + bookingId) ;

        }

    public String getToken(){
        System.out.println("Returning token: " + token);
        return token;
    }

    public String getBookingId(){
        System.out.println("Returning bookingId: " + bookingId);
        return bookingId;
    }


    @Description("Verify the created booking is able to update")
        @Test(priority = 2,dependsOnMethods = "test_create_booking")
         public void update_booking(){

                      System.out.println("Booking ID from update_booking:" + bookingId );

            String payload= "{\n" +
                    "    \"firstname\" : \"hemanth\",\n" +
                    "    \"lastname\" : \"Kumar\",\n" +
                    "    \"totalprice\" : 111,\n" +
                    "    \"depositpaid\" : true,\n" +
                    "    \"bookingdates\" : {\n" +
                    "        \"checkin\" : \"2025-01-01\",\n" +
                    "        \"checkout\" : \"2025-01-01\"\n" +
                    "    },\n" +
                    "    \"additionalneeds\" : \"Breakfast\"\n" +
                    "}";

            requestSpecification.basePath("/booking/" + bookingId);
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.accept(ContentType.JSON);
            requestSpecification.cookie("token",token);
            requestSpecification.body(payload).log().all();

            Response response=requestSpecification.when().log().all().put();
            response.then().log().all().statusCode(418);
        }




    }

