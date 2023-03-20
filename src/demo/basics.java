package demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import File.ReUsableMethods;
import File.payload;

public class basics {

	public static void main(String[] args) {
		// Given - all input details
		// When - Submit the api - response & http methods
		// Then - validate the Response
		
		//Add Place
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String responce = 	given().log().all().queryParam("Key", "qaclick123").header("Content-Type","application/json")
		.body(payload.Addplace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(responce);
		
		JsonPath js = new JsonPath(responce);
		String PlaceID = js.getString("place_id");
		
		System.out.println(PlaceID);
		
		//Update Place
		
		String newAddress = "vedavathi nagara, HYR";
		given().log().all().queryParam("Key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+PlaceID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

		
		
		//Get Place
		
		String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
			.queryParam("place_id",PlaceID)
			.when().get("maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress =js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
		//Cucumber Junit, Testng
		
		
		
		
	}

}
