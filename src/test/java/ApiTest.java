import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

public class ApiTest {

  ApiControllerGoRest controller = new ApiControllerGoRest();

  static int requireId = 10355;
  /*
  Hi Ka. I already tried many times to use id 10355, but it's not working
  I always get this error "data": [ { "field": "post", "message": "must exist" } whenever I already put post in my request body
  Also, I've tried to use that CURL for delete first before use that id but I still same condition.
  I can delete other id of post but can't delete id 10355. cURL:
  curl --location --request DELETE 'https://gorest.co.in/public/v1/comments/10355' \
  --header 'Authorization: Bearer b6b7a2af83d1b4037cb1c78c881cd508398086b2f746c04d22b1e837714a8002' \
  --header 'Cookie: _gorest_session=LbGwt7jTSX5dyHlSyUiZj5kzJJCCad88qHZiJFYin8ls0eqr4Jy7yfAFaNPw7HEygBlea9bRo7ZBuZWrTPSZvMk8Q1m
  0GFHjSWtYcUgjD4POggDRTMva2ewYCB6uUhzSxvMhKxTbmdfEHleWKNl2TUohksFeMtgW8HZY3UC4AOaTlLWNbMATFFHSa%2B4iLx%2BrTV%2BUxoloCLv5kpWzgMt
  EUx9hNarMRBpJMMu2cqM90Y88PzJcV7T1NzpDbwN9maB2KERRoH9wtbAL%2FJYgAJ4MkJSM5RSucMQ%3D--re4DLVs2LoOu9u1l--LOvCUlLsltg2QSuClQiPzg%3D%3D'
  and some users on gorest are facing same issue like me. Hope you understand.
  */

  @Test
  public void testPost() {
    Response response = controller.postCreateNewComment(29253);
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
