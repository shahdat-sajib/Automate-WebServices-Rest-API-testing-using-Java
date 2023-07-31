package TestingPackage.ApiTestingJava;

import TestingPackage.Classes.Posts;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;

public class JsonServerRequest {
    //GET Request
    @Test
    public void test_01() {
        Response res =
                given()
                        .when()
                        .get("http://localhost:3000/posts");
        System.out.println("Response from json server: " + res.asString());
    }

    //POST Request
    //@Test
    public void test_02() {
        Response res =
                given()
                        .body("{\n" +
                                "    \"id\": 888,\n" +
                                "    \"title\": \"json-server\",\n" +
                                "    \"author\": \"Shahdat Hosain 02\"\n" +
                                "  }")
                        .when()
                        .contentType(ContentType.JSON)
                        .post("http://localhost:3000/posts");
        System.out.println("Response: " + res.asString());
    }

    //Post request using class
    @Test
    public void test_03() {
        Posts posts = new Posts();
        posts.setId("122");
        posts.setTitle("Test 04");
        posts.setAuthor("Shahdat 04");

        Response res =
                given()
                        .when()
                        .contentType(ContentType.JSON)
                        .body(posts)
                        .post("http://localhost:3000/posts");
        System.out.println("Response Code: " + res.statusCode() + "\nResponse: " + res.asString());
    }
}
