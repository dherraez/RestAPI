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


public class H_Jira_3_Update_Comment {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getDataProp() throws IOException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\dherraez\\eclipse-workspace\\DemoRESTProject\\src\\Files\\env.properties");
		prop.load(fis);
	}
	
	
	@Test
	public void JiraAPIUpdate() {
		
		//First we need to create the session
		RestAssured.baseURI = prop.getProperty("HOSTJIRA");
		Response res = given().
		header("Content-Type","application/json").
		header("cookie", "JSESSIO=" + reusableMethods.getSessionID()).
		body("{\r\n" + 
				"    \"body\": \"Updated comment 18:50\"\r\n" + 
				"}").
		log().body().when().
		put("/rest/api/2/issue/10031/comment/" + reusableMethods.getCommentID()).
		then().log().body().
		assertThat().statusCode(200).
		extract().response();
		
		JsonPath jsp = reusableMethods.rawToJson(res);
		//String IssueID = jsp.get("id");
		//System.out.println(IssueID);
		
	}

}
