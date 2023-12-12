Feature: Add Place API Validation
@AddPlace
Scenario Outline: Verify place is added successfully
	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" using http "Post" request
	Then API call is successful
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And Verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name   |address           |language|
	|AAhouse|World Cross Center|English |
#	|BBhouse|World Center      |French  |

#@DeletePlace
#Scenario: Verify delete place functionality
	#Given Delete place payload
	#When user calls "deletePlaceAPI" using http "Post" request
	#Then API call is successful
	#And "status" in response body is "OK"