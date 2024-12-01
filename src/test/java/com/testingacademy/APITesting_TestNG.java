package com.testingacademy;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITesting_TestNG {

    @Test

    public void Test_get_request(){

        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/1")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);
    }
}
