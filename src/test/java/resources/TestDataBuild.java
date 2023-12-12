package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace a = new AddPlace();
		a.setAccuracy(50);
		a.setAddress(address);
		a.setLanguage(language);
		a.setName(name);
		a.setPhone_number("\"(+91) 983 893 3937");
		a.setWebsite("http://google.com");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe Park");
		myList.add("shop");
		a.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		a.setLocation(l);
		return a;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
