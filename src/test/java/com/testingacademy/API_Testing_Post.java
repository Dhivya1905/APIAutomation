package com.testingacademy;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static com.testingacademy.API_Test_Non_BDD_Style.r;
import static io.restassured.RestAssured.when;

public class API_Testing_Post {



        @Description("TC1-Verify the NonBDD Style-Post")
        @Test

        public void test__BDD_Style_Post() {

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";



            RestAssured
                    .given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/auth")
                    .contentType("application/json")
                    .log().all().body(payload)

                    .when().log().all().post()

                    .then().log().all().statusCode(200);
        }
    }
