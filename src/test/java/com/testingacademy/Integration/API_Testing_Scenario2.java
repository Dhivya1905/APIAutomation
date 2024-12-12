package com.testingacademy.Integration;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Testing_Scenario2 {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
   String payload;
   String bookingId;

   public String get_Token(){

       payload="{\n" +
               "    \"username\" : \"admin\",\n" +
               "    \"password\" : \"password123\"\n" +
               "}";

       requestSpecification= RestAssured.given();
       requestSpecification.baseUri("https://restful-booker.herokuapp.com");
       requestSpecification.basePath("/auth");
       requestSpecification.contentType(ContentType.JSON);
       requestSpecification.body(payload).log().all();


       response=requestSpecification.when().log().all().post();

       validatableResponse=response.then().log().all();
       validatableResponse.statusCode(200);

      token=response.jsonPath().getString("token");
      System.out.println(token);
      return token;
   }

   public String get_bookingId(){

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


       requestSpecification=RestAssured.given();
      requestSpecification.baseUri("https://restful-booker.herokuapp.com");
      requestSpecification.basePath("/booking");
      requestSpecification.contentType(ContentType.JSON);
       requestSpecification.body(payload).log().all();

      response=requestSpecification.when().log().all().post();

      validatableResponse=response.then().log().all();
      validatableResponse.statusCode(200);

       bookingId=response.jsonPath().getString(bookingId);
System.out.println(bookingId);
       return bookingId;
   }

//1. Create Booking -> Verify GET

    @Description("Verify the BookingID is created successfully")
    @Test(priority = 1)
    public  void  create_booking(){

        payload="{\n" +
                "    \"firstname\" : \"Dhivya\",\n" +
                "    \"lastname\" : \"R\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
       requestSpecification.cookie("token",token);
        requestSpecification.body(payload).log().all();

        response=requestSpecification.when().log().all().post();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        bookingId= response.jsonPath().getString("bookingid");
        System.out.println(bookingId);
    }


    @Description("Verify the Created Booking using Get method")
    @Test(priority = 2)
    public void get_Created_Booking(){

        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+ bookingId);

        response=requestSpecification.when().get();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

    }


}

