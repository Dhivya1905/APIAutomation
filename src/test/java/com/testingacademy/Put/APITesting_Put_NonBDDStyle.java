package com.testingacademy.Put;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_Put_NonBDDStyle {

//token :    "token": "4825b52829e214f"

    @Description("verify the PUT request for the Restful Booker APIs")
    @Test
    public void test_put_non_bdd()


    {

        //RequestSpecification rs;
        String token = "12cbc2e96df3cf1";
        String bookingid = "845";

        String payload = "{\n" +
                "    \"firstname\" : \"Dhivya\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        RequestSpecification rs = RestAssured.given();
        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking/" + bookingid);
        rs.contentType(ContentType.JSON);
        rs.cookie("token", token);
        rs.body(payload).log().all();

        Response response= rs.when().put();

        ValidatableResponse validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

    }

}