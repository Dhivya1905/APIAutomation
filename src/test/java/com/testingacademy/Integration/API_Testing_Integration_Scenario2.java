package com.testingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class API_Testing_Integration_Scenario2 {


    private String bookingId;
    private String token;
    private RequestSpecification requestSpecification;

    public API_Testing_Integration_Scenario2()
    {
        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
    }

    public String authenticate() {
        String payload = "{\n" + " \"username\" : \"admin\",\n" + " \"password\" : \"password123\"\n" + "}";
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload).log().all();
        Response response = requestSpecification.post();

        response.then().log().all().statusCode(200);
        token = response.jsonPath().getString("token");
        System.out.println("Token: " + token);
        return token;
    }

    public String create_booking()
    {
String payload="{\n" +
        "    \"firstname\" : \"Thaniya\",\n" +
        "    \"lastname\" : \"Devi\",\n" +
        "    \"totalprice\" : 111,\n" +
        "    \"depositpaid\" : true,\n" +
        "    \"bookingdates\" : {\n" +
        "        \"checkin\" : \"2024-01-01\",\n" +
        "        \"checkout\" : \"2024-02-01\"\n" +
        "    },\n" +
        "    \"additionalneeds\" : \"Lunch\"\n" +
        "}";

requestSpecification.basePath("/booking");
requestSpecification.contentType(ContentType.JSON);
requestSpecification.body(payload).log().all();

        Response response= requestSpecification.when().log().all().post();

        ValidatableResponse validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        bookingId=response.jsonPath().getString("bookingid");
        System.out.println("BookingID:"+ bookingId );
        return bookingId;

    }

    public String getBookingId(){

        return bookingId;
    }

    public String getToken(){

        return token;
    }


}
