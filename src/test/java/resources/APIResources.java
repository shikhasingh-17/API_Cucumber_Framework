package resources;

public enum APIResources {
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/");
	private String resource;

	APIResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
	
	
	

}
