import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {
  @Test
  @DisplayName("Get запрос")
  public void searchType() {
    JsonPath response =
        given()
            .when()
            .get(ConfigReader.getParam("api.searchApiUrl"))
            .then()
            .assertThat()
            .statusCode(SC_OK)
            .contentType(JSON)
            .extract()
            .jsonPath();

    List<String> suggestions = response.getJsonObject("suggestions.term");
    List<String> titles = response.getJsonObject("pages.title");

    checkValueType(suggestions, "<span class=\"search-highlight\">java</span>", 4);
    checkValueType(titles, "Wiley", 4);
  }

  private void checkValueType(List<String> actualValueType, String extendValueType, int countVariable) {
    assertTrue(
        (actualValueType.stream().allMatch((s) -> s.contains(extendValueType)))
            && (actualValueType.size() == countVariable),
        countVariable + " sentences do not contain the type " + extendValueType);
  }

  @Test
  @DisplayName("Post request delay 10 seconds. option 1")
  public void postDelayTest() {
    Response response =
        given()
            .when()
            .header("accept", "application/json")
            .post(ConfigReader.getParam("api.delayApiUrl"))
            .then()
            .assertThat()
            .statusCode(SC_OK)
            .contentType(JSON)
            .extract()
            .response();

    assertTrue((response.time() <= 10000), ("Returns a deferred response exceeding 10 seconds"));
  }

  @Test
  @DisplayName("Post request delay 10 seconds. option 2")
  public void postDelayTest2() {
    given()
        .when()
        .header("accept", "application/json")
        .post(ConfigReader.getParam("api.delayApiUrl"))
        .then()
        .time(lessThan(10000L))
        .assertThat()
        .statusCode(SC_OK)
        .contentType(JSON)
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
            .get(ConfigReader.getParam("api.ImageApiUrl"))
            .then()
            .assertThat()
            .statusCode(SC_OK)
            .contentType("image/png")
            .extract()
            .body()
            .asInputStream();

    assertTrue((ImageDiffer.checkImage(response)), ("Image do not match"));
  }
}
