import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {
    @Test
    @DisplayName("Get запрос")
    public void oneTest() {
        JsonPath response =
                given()
                        .when()
                        .get(ConfigReader.getParam("apiUrl"))
                        .then()
                        .log()
                        .all()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .jsonPath();

        List<String> actualNameSuggestions = response.getJsonObject("suggestions.term");
        List<String> actualNameTitle = response.getJsonObject("pages.title");

    assertTrue(
        ((actualNameSuggestions.stream()
                .allMatch((s) -> s.contains("<span class=\"search-highlight\">java</span>"))))
            && (actualNameSuggestions.size() == 4),
        String.format("4 sentences do not start with a preformatted highlighted word java within the type <span class=\"search-highlight\">java</span>"));

        assertTrue(
                ((actualNameTitle.stream().allMatch((s) -> s.contains("Wiley")))) && (actualNameTitle.size() == 4),

                String.format("4 sentences do not contain the attribute 'term' value includes word Wiley "));
    }


    @Test
    @DisplayName("Post request delay 10 seconds. option 1")
    public void postDelayTest() {
        Response response =
                given()
                        .when()
                        .header("accept", "application/json")
                        .post(ConfigReader.getParam("apiDelayUrl"))
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();

        assertTrue((response.time() <= 10000), String.format("Returns a deferred response exceeding 10 seconds"));
    }

    @Test
    @DisplayName("Post request delay 10 seconds. option 2")
    public void postDelayTest2() {
        given()
                .when()
                .header("accept", "application/json")
                .post(ConfigReader.getParam("apiDelayUrl"))
                .then()
                .time(lessThan(10000L))
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }

    @Test
    @DisplayName("Get image format PNG")
    public void getImageTest() throws IOException {
        Object response =
                given()
                        .when()
                        .header("accept", "image/png")
                        .get(ConfigReader.getParam("apiImageUrl"))
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .body()
                        .asInputStream();

        assertTrue((ImageDiffer.checkImage("apiPathImage", response)), String.format("Image do not match"));
    }
}
