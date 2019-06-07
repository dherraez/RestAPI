package TestFramework;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.google.common.io.Resources;

import googleAPI.payLoad;
import googleAPI.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class B_Basics_POST {
	
	@Test
	public void createPlaceAPI() {
		
		//THis first part is to create a new place
		//BaseURL or Host
		RestAssured.baseURI="http://216.10.245.166";
		given().
		queryParam("key","qaclick123").
		body(payLoad.AddPlaceBody()).
			when().
			post(resources.getAddPlaceResource()).
			then().log().body().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status",equalTo("OK"));
		
		//The place is created
		//Now I want to delete it. See in POST_ADD and Delete Test
		
		
		
	}

}
