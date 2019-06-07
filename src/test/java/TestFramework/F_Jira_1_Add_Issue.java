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


public class F_Jira_1_Add_Issue {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getDataProp() throws IOException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\dherraez\\eclipse-workspace\\DemoRESTProject\\src\\Files\\env.properties");
		prop.load(fis);
	}
	
	
	@Test
	public void JiraAPICreateIssue() {
		
		//First we need to create the session
		RestAssured.baseURI = prop.getProperty("HOSTJIRA");
		Response res = given().
		header("Content-Type","application/json").
		header("cookie", "JSESSIONID=" + reusableMethods.getSessionID()).
		body("{\r\n" + 
				"    \"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\r\n" + 
				"          \"key\": \"PROJ\"\r\n" + 
				"       },\r\n" + 
				"       \"summary\": \"Credit Card Defect 2 via API\",\r\n" + 
				"       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n" + 
				"       \"issuetype\": {\r\n" + 
				"          \"name\": \"Bug\"\r\n" + 
				"       }\r\n" + 
				"   }\r\n" + 
				"}").
		log().body().when().
		post("/rest/api/2/issue").
		then().log().body().
		assertThat().statusCode(201).
		extract().response();
		
		JsonPath jsp = reusableMethods.rawToJson(res);
		String IssueID = jsp.get("id");
		System.out.println(IssueID);
		
	}

}
