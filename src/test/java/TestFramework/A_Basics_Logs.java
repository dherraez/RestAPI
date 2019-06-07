package TestFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class A_Basics_Logs {

	
	
	@Test
	public void getPlaceAPI() {
		// TODO Auto-generated method stub
		
		//BaseURL or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//Parametros con given
		Response res = given().log().all().
				param("location","-33.8670522,151.1957362").
				param("radius","1500").
				param("key","AIzaSyALhP4ycBw4amcU68BZZTqpKyfxscguUJU").
				//Resources dentro del When
				when().
				get("/maps/api/place/nearbysearch/json").
				then().log().all().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("results[0].types[2]",equalTo("political")).and().body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
				and().header("Server",equalTo("scaffolding on HTTPServer2")).
				extract().response();
				

	}



}
