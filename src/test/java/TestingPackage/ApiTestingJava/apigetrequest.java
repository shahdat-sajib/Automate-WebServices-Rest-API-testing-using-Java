package TestingPackage.ApiTestingJava;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;

public class apigetrequest {

    //Simple GET request with status 200
    //@Test
    public void Test_01() {
        Response res = when().get("https://api.openweathermap.org/data/2.5/weather?q=Bangladesh&appid=801772ada32df1f5743ceb9baf3b461a");

        System.out.println("Response Status: " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    //Status : 401
    //@Test
    public void Test_02() {
        Response res = when().get("https://api.openweathermap.org/data/2.5/weather?q=Bangladesh&appid=123456789");

        System.out.println("Response Status: " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 201);
    }

    //Using parameters
    //@Test
    public void Test_03() {
        Response res =
                given()
                        .param("q", "Bangladesh")
                        .param("appid", "801772ada32df1f5743ceb9baf3b461a")
                        .when()
                        .get("https://api.openweathermap.org/data/2.5/weather");

        System.out.println("Response Status: " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200);
        if (res.getStatusCode() == 200) {
            System.out.println("API is working with parameters");
        } else {
            System.out.println("API is NOT working with parameters");
        }
    }

    //Aeert in rest assured api
    //@Test
    public void Test_04() {
        given()
                .param("q", "Bangladesh")
                .param("appid", "801772ada32df1f5743ceb9baf3b461a")

                .when()
                .get("https://api.openweathermap.org/data/2.5/weather")

                .then()
                .assertThat().statusCode(200);
    }

    //Whole response handeling
    //@Test
    public void Test_05() {
        Response res =
                given()
                        .param("q", "Bangladesh")
                        .param("appid", "801772ada32df1f5743ceb9baf3b461a")

                        .when()
                        .get("https://api.openweathermap.org/data/2.5/weather");

        System.out.println("Response:\n" + res.asString());
    }

    //Extracting actual value and assert with expected value
   // @Test
    public void Test_06() {
        Response res =
                given()
                        .param("q", "Bangladesh")
                        .param("appid", "801772ada32df1f5743ceb9baf3b461a")

                        .when()
                        .get("https://api.openweathermap.org/data/2.5/weather");

        String actualWeatherDetails =
                res
                        .then()
                        .contentType(ContentType.JSON)
                        .extract()
                        .path("weather[0].description");

        String expectedWeatherReport = "few clouds";
        if(actualWeatherDetails.equalsIgnoreCase(expectedWeatherReport)){
            System.out.println("Test Passed");
        }else{
            Assert.assertEquals(expectedWeatherReport, actualWeatherDetails);
        }
    }

    //@Test
    public void Test_07() {
        String weatherDescription =
                given()
                        .param("q", "Bangladesh")
                        .param("appid", "801772ada32df1f5743ceb9baf3b461a")

                        .when()
                        .get("https://api.openweathermap.org/data/2.5/weather")
                        .then()
                        .contentType(ContentType.JSON)
                        .extract()
                        .path("weather[0].description");

        System.out.println("The weather description: " + weatherDescription);
    }

    @Test
    public void Test_08() {
        Response res =
                given()
                        .param("q", "Bangladesh")
                        .param("appid", "801772ada32df1f5743ceb9baf3b461a")

                        .when()
                        .get("https://api.openweathermap.org/data/2.5/weather");

        String actualWeatherDescription =
                res
                        .then()
                        .contentType(ContentType.JSON)
                        .extract()
                        .path("weather[0].description");

        String expectedWeatherReport = "overcast clouds";

        Assert.assertEquals(actualWeatherDescription, expectedWeatherReport);

    }

}
