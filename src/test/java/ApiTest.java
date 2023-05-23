import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

public class ApiTest {

  ApiControllerGoRest controller = new ApiControllerGoRest();


  @Test
  public void testPost() {
    Response response = controller.postCreateNewComment();
    // assert
    MatcherAssert.assertThat(response.then().extract().body().asString(),
        matchesJsonSchemaInClasspath("templates/schema-comment-post.json"));
    response.then().statusCode(201)
        .and().body("data.post_id", equalTo(29253))
        .and().body("data.name", equalTo("Rocky Gerunk"))
        .and().body("data.email", equalTo("rg99@mailinator.com"))
        .and().body("data.body", equalTo("Hello! Hawayu?"));
  }

  @Test
  public void testGet() {
    Response response = controller.getPostsByTitleAndBody();
    // assert
    MatcherAssert.assertThat(response.then().extract().body().asString(),
        matchesJsonSchemaInClasspath("templates/schema-posts-get.json"));
    response.then().statusCode(200)
        .and().body("meta.pagination.total", equalTo(0))
        .and().body("meta.pagination.pages", equalTo(0))
        .and().body("meta.pagination.page", equalTo(1))
        .and().body("meta.pagination.limit", equalTo(10));
  }
}
