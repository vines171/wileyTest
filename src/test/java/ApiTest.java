import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

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

        Assert.assertTrue(actualNameSuggestions.stream().allMatch((s) -> s.contains("<span class=\"search-highlight\">java</span>")));
        Assert.assertTrue(actualNameTitle.stream().allMatch((s) -> s.contains("Wiley")));
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

        Assert.assertTrue(response.time() < 10000);
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

        Assert.assertTrue((ImageDiffer.checkImage("apiPathImage", response)));
    }
}
