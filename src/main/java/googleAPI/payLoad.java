package googleAPI;

public class payLoad {

	public static String getBodyAddPlace() {

		// Grabbing the Requestbody in one string
		String BodyaddPlace = "{ " + "    \"location\":{" + "        \"lat\" : -38.383494,"
				+ "        \"lng\" : 33.427362" + "    }," + "    \"accuracy\":50,"
				+ "    \"name\":\"Frontline house\"," + "    \"phone_number\":\"(+91) 983 893 3937\","
				+ "    \"address\" : \"29, side layout, cohen 09\"," + "    \"types\": [\"shoe park\",\"shop\"],"
				+ "    \"website\" : \"http://google.com\"," + "    \"language\" : \"French-IN\"" + "}";

		return BodyaddPlace;
	}

	public static String getBodyBasic2() {
		String BodyBasic2 = "{ " + "    \"location\":{" + "        \"lat\" : -38.383494,"
				+ "        \"lng\" : 33.427362" + "    }," + "    \"accuracy\":50,"
				+ "    \"name\":\"Frontline house\"," + "    \"phone_number\":\"(+91) 983 893 3937\","
				+ "    \"address\" : \"29, side layout, cohen 09\"," + "    \"types\": [\"shoe park\",\"shop\"],"
				+ "    \"website\" : \"http://google.com\"," + "    \"language\" : \"French-IN\"" + "}";
		return BodyBasic2;
	}

	public static String addBookBody(String isbn, String aisle) {
		String PayloadAddBook = "{" + "\"name\":\"Learn Davids book\"," + "\"isbn\":\"" + isbn + "\"," + "\"aisle\":\""
				+ aisle + "\"," + "\"author\":\"David Her\"" + "}";
		return PayloadAddBook;

	}

	public static String DeleteBook(String isbn, String aisle) {
		String PayLoadDelete = "{\\n\\\"ID\\\" : \\\"" + isbn + aisle + "\\\"\\n} ";
		return PayLoadDelete;
	}

	public static String AddPlaceBody() {
		String AddPlaceBody = "{ " + "    \"location\":{" + "        \"lat\" : -38.383494,"
				+ "        \"lng\" : 33.427362" + "    }," + "    \"accuracy\":50,"
				+ "    \"name\":\"Frontline house\"," + "    \"phone_number\":\"(+91) 983 893 3937\","
				+ "    \"address\" : \"29, side layout, cohen 09\"," + "    \"types\": [\"shoe park\",\"shop\"],"
				+ "    \"website\" : \"http://google.com\"," + "    \"language\" : \"French-IN\"" + "}";

		return AddPlaceBody;
	}

}
