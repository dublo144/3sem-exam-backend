package rest;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RecipeResourceTest extends BaseResourceTest {

    @Test
    public void testGetRecipes() {
        given()
                .get("recipes")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("list.size()", is(4));
    }

    @Test
    public void testGetRecipeDetailsById() {
        given()
                .get("recipes/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("name", equalToIgnoringCase("Simple Sauerkraut"));
    }
}
