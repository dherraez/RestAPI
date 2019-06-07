package googleAPI;

public class resources {
	
	public static String placePostData() {
		
		String SpostAddResource = "/maps/api/place/add/json";
		return SpostAddResource;
		
	}
	
	public static String getDeletePostData() {
		String SpostDeleteResource = "/maps/api/place/delete/json";
		return SpostDeleteResource;
		
	}
	
	public static String getSearchAPIGoogle() {
		
		String SgetGoogleAPISearch = "/maps/api/place/nearbysearch/json";
		return SgetGoogleAPISearch;
	}
	
	public static String getAddPlaceResource() {
		String addPlaceResource = "/maps/api/place/add/json";
		return addPlaceResource;
		
	}

}
