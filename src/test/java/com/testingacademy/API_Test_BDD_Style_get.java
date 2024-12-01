    package com.testingacademy;

    import io.restassured.RestAssured;
    import org.testng.annotations.Test;

    public class API_Test_BDD_Style_get {

        @Test

        public void test_get_response_Postive(){

            String pincode="600078";

            RestAssured
                    .given()
                       .baseUri("https://api.zippopotam.us")
                       .basePath("/IN/"+pincode )
                    .when()
                        .get()
                    .then()
                         .log().all()
                          .statusCode(200);

        }

        @Test
        public void test_get_response_Negative(){

            String pincode="-1";

            RestAssured
                    .given()
                    .baseUri("https://api.zippopotam.us")
                    .basePath("/IN/"+pincode )
                    .when()
                    .get()
                    .then()
                    .log().all()
                    .statusCode(404);

        }

    }

