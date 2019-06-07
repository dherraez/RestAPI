package TestFramework;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestFramework.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class G_Jira_2_Add_Comment {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getDataProp() throws IOException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\dherraez\\eclipse-workspace\\DemoRESTProject\\src\\Files\\env.properties");
		prop.load(fis);
	}
	
	
	@Test
	public void JiraAPICreateComment() {
		
		//First we need to create the session
		RestAssured.baseURI = prop.getProperty("HOSTJIRA");
		Response res = given().
		header("Content-Type","application/json").
		header("cookie", "JSESSIONID=" + reusableMethods.getSessionID()).
		body("{\r\n" + 
				"    \"body\": \"This is a comment regarding the quality of the response for issue 10031.\"\r\n" + 
				"}").
		log().body().when().
		post("/rest/api/2/issue/10031/comment").
		then().log().body().
		//assertThat().statusCode(201).
		extract().response();
		
		JsonPath jsp = reusableMethods.rawToJson(res);
		String CommentID = jsp.get("id");
		System.out.println("CommentID is = " + CommentID);
		//return CommentID;
		
	}

}
