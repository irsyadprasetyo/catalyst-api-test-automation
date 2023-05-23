import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiControllerGoRest {

  private static final String BASE_URL = "https://gorest.co.in";
  private static final String ACCESS_TOKEN = "b6b7a2af83d1b4037cb1c78c881cd508398086b2f746c04d22b1e837714a8002";

  public Response getPostsByTitleAndBody() {
    Response response = given().baseUri(BASE_URL).basePath("/")
        .contentType(ContentType.JSON)
        .header("Authorization", ACCESS_TOKEN)
        .queryParam("title_like", "Catalyst")
        .queryParam("body", "BUILD TRUST EMPOWER OTHERS").get("/public/v1/posts");

    response.getBody().prettyPrint();
    return response;
  }

  public Response postCreateNewComment(int id) {
    String request = "{\"post\": "+ id +", \"post_id\": "+ id +", \"name\": \"Rocky Gerunk\", \"email\": \"rg99@mailinator.com\", "
        + "\"gender\": \"male\", \"status\": \"married\", \"body\": \"Hello! Hawayu?\"}";

    Response response = given().baseUri(BASE_URL).basePath("/")
        .contentType(ContentType.JSON)
        .header("Authorization", "Bearer " + ACCESS_TOKEN)
        .body(request).when().post("/public/v1/comments");

    response.getBody().prettyPrint();
    return response;
  }
}
