package ua.beast.oun.rest.tests;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import ua.beast.oun.rest.appmanager.ApplicationManager;
import ua.beast.oun.rest.models.AuthenticationToken;
import ua.beast.oun.rest.models.Credentials;

import static com.jayway.restassured.RestAssured.given;


public class TestBase {
    AuthenticationToken authToken;

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeClass
    public void init() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        Credentials credentials = new Credentials();
        credentials.setUsername("admin");
        credentials.setPassword("password123");

        authToken = given().accept("application/json")
                .contentType("application/json").body(credentials).expect()
                .statusCode(200)
                .when().post("/auth")
                .then().log().all()
                .extract().body().as(AuthenticationToken.class);
    }

}


