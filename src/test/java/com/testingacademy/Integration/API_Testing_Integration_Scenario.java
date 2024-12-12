package com.testingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Testing_Integration_Scenario {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String token;
    String bookingId;

    public String gettoken(){
       String payload="{\n" +
               "    \"username\" : \"admin\",\n" +
               "    \"password\" : \"password123\"\n" +
               "}";
requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload).log().all();

        response=requestSpecification.when().post();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
 token = response.jsonPath().getString("token");
 System.out.println(token);

       return token;
    }

    public String getbookingID(){

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

        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload).log().all();

        response=requestSpecification.when().post();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        bookingId = response.jsonPath().getString("bookingid");
        System.out.println(bookingId);
        return bookingId;
    }
    @Test(priority = 1)
    public void  test_update_request_put() {

        String token = gettoken();
        String bookingId=getbookingID();
        System.out.println(token);
        System.out.println(bookingId);

        String payload="{\n" +
                "    \"firstname\" : \"Pradeep\",\n" +
                "    \"lastname\" : \"Kumar\",\n" +
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
        requestSpecification.basePath("/booking/"+ bookingId);
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.cookie( "token",token);
        requestSpecification.body(payload).log().all();

        response=requestSpecification.when().put();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

    }

    @Test(priority = 2)
    public void  test_get_request() {

        System.out.println(bookingId);

        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+ bookingId);


        response=requestSpecification.when().log().all().get();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
         String first_name = response.jsonPath().getString("firstname");
        String Last_name = response.jsonPath().getString("Lastname");

    }

    @Test(priority = 3)
    public void  test_delete_request() {

        System.out.println(bookingId);
        System.out.println(token);


        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+ bookingId);
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.cookie( "token",token);


        response=requestSpecification.when().delete();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);//#Todo bug

    }

    @Test(priority = 4)
    public void  test_get_Deleted_request() {



        requestSpecification= RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+ bookingId);


        response=requestSpecification.when().log().all().get();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(404);

    }
//#todo 2 item


}

