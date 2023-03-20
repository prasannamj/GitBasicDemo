package demo;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;



import File.ReUsableMethods;
import File.payload;

public class DynamicJson {
	
	public static void main(String[]args)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type","application/json")
		.body(payload.Addbook("mjp","994")).when().post("Library/Addbook.php ")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.getString("ID");
		System.out.println(id);
		
	}
}
