package com.testingacademy;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Test_Non_BDD_Style {


   static  RequestSpecification r= RestAssured.given();

    @Description("TC1-Verify the NonBDD Style-Postive")

    @Test

    public void test_non_BDD_Style_get_Positive(){


        r.baseUri("https://api.zippopotam.us/");
        r.basePath("IN/600078");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }

    @Description("TC2-Verify the NonBDD Style-Negative")
    @Test

    public void test_non_BDD_Style_get_negative(){

        r.baseUri("https://api.zippopotam.us/");
        r.basePath("IN/-1");
        r.when().log().all().get();
        r.then().log().all().statusCode(404);
    }
}

