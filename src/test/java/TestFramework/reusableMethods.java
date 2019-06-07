package TestFramework;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class reusableMethods {

	public static XmlPath rawToXml(Response rawdata) {
		String Sresponse = rawdata.asString();
		XmlPath xp = new XmlPath(Sresponse);
		return xp;

	}

	public static JsonPath rawToJson(Response rawdata) {
		String Sresponse = rawdata.asString();
		JsonPath xp = new JsonPath(Sresponse);
		return xp;

	}

	public static String getSessionID() {

		// First we need to create the session
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"davidhg\", \"password\": \"davo1986\" }").when().post("/rest/auth/1/session")
				.then().log().body().assertThat().statusCode(200).extract().response();

		JsonPath JiraID = reusableMethods.rawToJson(res);
		String SessionID = JiraID.get("session.value");
		return SessionID;

	}

	public static String getCommentID() {

		// Adding a comment
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type", "application/json")
				.header("cookie", "JSESSIONID=" + reusableMethods.getSessionID())
				.body("{\r\n"
						+ "    \"body\": \"This is a comment regarding the quality of the response for issue 10031.\"\r\n"
						+ "}")
				.log().body().when().post("/rest/api/2/issue/10031/comment").then().log().body().
				// assertThat().statusCode(201).
				extract().response();

		JsonPath jsp = reusableMethods.rawToJson(res);
		String CommentID = jsp.get("id");
		return CommentID;

	}

}
