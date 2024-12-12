import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Testing_Post_NonBDD {

    @Description("TC1-Verify the NonBDD Style-Post")
    @Test

    public void test__BDD_Style_Post() {

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

RequestSpecification r= RestAssured.given();
               r .baseUri("https://restful-booker.herokuapp.com");
                r.basePath("/auth");
                r.contentType("application/json");
                r.log().all().body(payload);

                r.when().log().all().post();

                r.then().log().all().statusCode(200);
    }
}

