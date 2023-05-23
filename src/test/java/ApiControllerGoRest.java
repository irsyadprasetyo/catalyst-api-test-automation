import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiControllerGoRest {

  private static final String BASE_URL = "https://gorest.co.in";
  private static final String ACCESS_TOKEN = "b9e6cf08ae864edf21f793e05331333a416a72a40774ac5a474c8b75ccb71c75";

  public Response getPostsByTitleAndBody() {
    Response response = given().baseUri(BASE_URL).basePath("/")
        .contentType(ContentType.JSON)
        .header("Authorization", ACCESS_TOKEN).queryParam("title_like", "Catalyst")
        .queryParam("body", "BUILD TRUST EMPOWER OTHERS").get("/public/v1/posts");

    response.getBody().prettyPrint();
    return response;
  }

  public Response postCreateNewComment() {
    String request = "{\"post\": 29253, \"post_id\": 29253, \"name\": \"Rocky Gerunk\", \"email\": \"rg99@mailinator.com\", "
        + "\"gender\": \"male\", \"status\": \"married\", \"body\": \"Hello! Hawayu?\"}";

    Response response = given().baseUri(BASE_URL).basePath("/")
        .contentType(ContentType.JSON)
        .header("Authorization", "Bearer " + ACCESS_TOKEN)
        .body(request).when().post("/public/v1/comments");

    response.getBody().prettyPrint();
    return response;
  }
}
