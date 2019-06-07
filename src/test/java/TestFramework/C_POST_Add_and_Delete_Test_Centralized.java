package TestFramework;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import googleAPI.resources;
import googleAPI.payLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class C_POST_Add_and_Delete_Test_Centralized extends resources {
	
	public static Logger log = LogManager.getLogger(C_POST_Add_and_Delete_Test_Centralized.class.getName());

	Properties prop = new Properties();
	
	@BeforeTest
	public void getDataProp() throws IOException {
		
		String localPath = System.getProperty("user.dir");
		//La linea anterior me da el path de mi proyecto y ya solo tengo que extenderlo con "//env.properties".
		FileInputStream fis = new FileInputStream(localPath + "//env.properties");
		prop.load(fis);
	}
	
	@Test
	public void AddandDeletePlace() throws IOException {

		

		//THis first part is to create a new place
		//BaseURL or Host
		//placePostData();
		log.info("Host Information " + prop.getProperty("HOST"));
		RestAssured.baseURI=prop.getProperty("HOST");
		Response res = given().
		queryParam("key", prop.getProperty("KEY")).
		body(payLoad.getBodyAddPlace()).
			when().
			post(resources.placePostData()).
			then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status",equalTo("OK")).
			extract().response()
			;
		
		//Grab the Place ID from response
		
		String Sres = res.asString();
		log.info("Response is: " + Sres);
		//NO need with log.info System.out.println(Sres);
		JsonPath js = new JsonPath(Sres);
		String Splace_id = js.get("place_id");
		log.info("Place_Id is: " + Splace_id);
		//NO needed with log.info System.out.println(Splace_id);
		
		//Now we need to place this place_id in the delete request to delete the record.
		Response RdelResp = given().
		queryParam("key",prop.getProperty("KEY")).
		body("{" + "\"place_id\":" + "\"" + Splace_id + "\"" + "}").
			when().
			post(resources.getDeletePostData()).
			then().statusCode(200).and().contentType(ContentType.JSON).and().body("status",equalTo("OK")).
			extract().response();
		
		String SdelResp = RdelResp.asString();
		JsonPath jsdelResp = new JsonPath(SdelResp);
		String SdelStatus = jsdelResp.get("status");
		System.out.println("Delete Status is:" + SdelStatus);
		
	
}
	
}
