package TestFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import TestFramework.reusableMethods;

import static io.restassured.RestAssured.given;

public class E_Basics_All_JSON_Nodes {

	
	
	@Test
	public void extractingNamesAPI() {
		// TODO Auto-generated method stub
		
		//BaseURL or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//Parametros con given
		Response res = given().
				param("location","-33.8670522,151.1957362").
				param("radius","1500").
				param("key","AIzaSyALhP4ycBw4amcU68BZZTqpKyfxscguUJU").
				//Resources dentro del When
				when().
				get("/maps/api/place/nearbysearch/json").
				then().log().body().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("results[0].types[2]",equalTo("political")).and().body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
				and().header("Server",equalTo("scaffolding on HTTPServer2")).
				extract().response();
		
		JsonPath jsp = reusableMethods.rawToJson(res);
		
		//Quiero leer todos los names dentro de los tags Result (que tengo 20)
		
		int count = jsp.get("results.size()");
		
		for (int i = 0; i < count; i++) {
			//jsp.get("results[i].name");
			System.out.println(jsp.get("results["+i+"].name").toString());
			
		}
		System.out.println(count);

	}



}
