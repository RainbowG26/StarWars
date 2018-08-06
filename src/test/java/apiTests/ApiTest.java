package apiTests;

import com.sun.org.glassfish.gmbal.Description;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class ApiTest {

    private Logger logger = Logger.getLogger(getClass());
    private String endPointPeople = "https://swapi.co/api/people/1/";
    private static String homeWorld;
    private static String nameFilm;

    public ApiTest() {
    }

    @Rule
    public JUnitSoftAssertions soft = new JUnitSoftAssertions();

    @Test
    @Description("Get name and find endPoint homeWorld")
    public void test01() {
        Response list = given()
                .contentType("application/json")
                .when()
                .get(endPointPeople)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        String name = list.jsonPath().get("name").toString();
        soft.assertThat(name)
                .as("Expected namePlanet - Luke Skywalker")
                .isEqualTo("Luke Skywalker");
        logger.info("Name is - " + name);

        homeWorld = list.jsonPath().get("homeworld").toString();
        soft.assertThat(homeWorld)
                .as("Expected namePlanet - https://swapi.co/api/planets/1/")
                .isEqualTo("https://swapi.co/api/planets/1/");
        logger.info("Home world endPoint - " + homeWorld);
    }

    @Test
    @Description("Get namePlanet/populationPlanet and find endPoint nameFilm")
    public void test02() {
        Response list = given()
                .contentType("application/json")
                .when()
                .get(homeWorld)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        String namePlanet = list.jsonPath().get("name");
        soft.assertThat(namePlanet)
                .as("Expected namePlanet - Tatooine")
                .isEqualTo("Tatooine");
        logger.info("Planet name - " + namePlanet);

        String populationPlanet = list.jsonPath().get("population").toString();
        soft.assertThat(populationPlanet)
                .as("Expected namePlanet - 200000")
                .isEqualTo("200000");
        logger.info("Population name - " + populationPlanet);

        nameFilm = list.jsonPath().get("films[0]").toString();
        logger.info("Film name - " + nameFilm);
    }

    @Test
    @Description("Get nameTitle and check arrayCharacters/arrayPlanets")
    public void test03() {
        Response list = given()
                .contentType("application/json")
                .when()
                .get(nameFilm)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        String nameTitle = list.jsonPath().get("title").toString();
        soft.assertThat(nameTitle)
                .as("Expected name title - Attack of the Clones")
                .isEqualTo("Attack of the Clones");
        logger.info("Name title film - " + nameTitle);

        ArrayList<String> arrayCharacters = list.jsonPath().get("characters");
        soft.assertThat(arrayCharacters.contains(endPointPeople))
                .as("Expected result - false")
                .isEqualTo(false);

        ArrayList<String> arrayPlanets = list.jsonPath().get("planets");
        soft.assertThat(arrayPlanets.contains(homeWorld))
                .as("Expected result - true")
                .isEqualTo(true);
    }
}
