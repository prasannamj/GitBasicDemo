package demo;

import org.testng.Assert;

import File.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	
	public static void main(String[] args)
	{
		JsonPath js = new JsonPath(payload.courseprice());
		
		//print the number of Courses return by Apis
		int Count = js.getInt("courses.size()");
		System.out.println(Count);
		
		//print purchase Amount
		int Pamount = js.getInt("dashboard.purchaseAmount");
		System.out.println(Pamount);
		
		//Print Title of the first course
		String FirstCourseTitle = js.getString("courses[0].title");
		System.out.println(FirstCourseTitle);
		
		
		//Print All course titles and their respective Prices
		

		for(int i =0;i<Count;i++)
		{
			String CourseName = js.getString("courses["+i+"].title");
			int CoursePrice = js.getInt("courses["+i+"].price");
			System.out.println(CourseName + " :" + CoursePrice);
		}

		
		//Print no of copies sold by RPA Course
		System.out.print("RPA COURSE Copies Numbers  ");

		for(int i =0;i<Count;i++)
		{
			String CourseName = js.getString("courses["+i+"].title");
			
			if(CourseName.equalsIgnoreCase("RPA"))
			{
				int CourseCopies = js.getInt("courses["+i+"].copies");
				System.out.println(CourseName + " :" +CourseCopies);

			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		
		int sum = 0;
		for(int j=0 ; j<Count;j++)
		{
			int Amount = js.getInt("courses["+j+"].price");
			int Copies = js.getInt("courses["+j+"].copies");
			int TotalAmountOfCourse = Amount * Copies;
			sum = sum  +TotalAmountOfCourse;
		}
			System.out.println("Sum of all the courses : " + sum);
			Assert.assertEquals(Pamount, sum);

		
		}
	}
