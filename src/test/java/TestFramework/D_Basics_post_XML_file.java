package TestFramework;
import org.testng.annotations.Test;

import TestFramework.reusableMethods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.xpath.XPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class D_Basics_post_XML_file {
	
	@Test
	public void postDataXML() throws IOException {
		
		String postBody = GenerateStringFromResource("C:\\Users\\dherraez\\eclipse-workspace\\DemoRESTProject\\src\\Files\\postdata.xml");
		
		//THis first part is to create a new place
		//BaseURL or Host
		RestAssured.baseURI="http://216.10.245.166";
		Response res = given().
		queryParam("key","qaclick123").
		body(postBody).
			when().
			post("/maps/api/place/add/xml").
			then().
			assertThat().statusCode(200).and().contentType(ContentType.XML).
			and().body("response.status",equalTo("OK")).
			and().body("response.scope", equalTo("APP")).
			extract().response();
		
		XmlPath xpresponse = reusableMethods.rawToXml(res);
		System.out.println("Place_ID Added is: " + xpresponse.get("response.place_id").toString());
		
		
		//The place is created
		//Now I want to delete it. See in POST_ADD and Delete Test
		
		
		
	}

	
	public static String GenerateStringFromResource (String path) throws IOException {
		
		return new String (Files.readAllBytes(Paths.get(path)));
	}
}
