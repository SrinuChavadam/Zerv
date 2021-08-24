package unittest;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutOperation {
	@Test
	public void getReaponses() {
		RestAssured.baseURI = "http://ccpublicationonboarding.dev-stable.cc.clarivate.com/acquisition/taskChecklists/publication/";
		RequestSpecification request = RestAssured.given();
	request.header("Content-Type", "application/json");
		request.header("SessionToken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMGMyMzdmMC1jN2IyLTExZTgtYmU3My02YmI4ZjU1YTNkODYiLCIxcDp0eXBlIjoiUEEiLCIxcDpwcmQiOiJhenVyZSIsIjFwOmFwcCI6ImplZGkiLCJpc3MiOiIxcCIsIjFwOmZubSI6IlBhcnRoaWJhbiIsIjFwOnRydWlkcyI6WyIxMGMyMzdmMC1jN2IyLTExZTgtYmU3My02YmI4ZjU1YTNkODYiXSwiMXA6ZW1sIjoiUGFydGhpYmFuLkdAY2xhcml2YXRlLmNvbS1DMzAwMDAzIiwiMXA6cHJvZHVjdHMiOltdLCIxcDpob3N0IjoiaHR0cHM6Ly9hY2Nlc3MuY2xhcml2YXRlLmNvbSIsIjFwOnBpZCI6IkMzMDAwMDMiLCIxcDpsbm0iOiJHIiwiMXA6dHB3ZCI6ZmFsc2UsIjFwOmxucyI6IjEwMCwxMDAiLCIxcDpsbHQiOjE1NTI1NDY0NzcwNjMsImV4cCI6MTU1MjU4OTY4MCwiaWF0IjoxNTUyNTQ2NDgwLCJqdGkiOiIxYmZlZjQ4Yi0yNGIxLTQ1NDctODNiZS0xMmZhMjhkMTdhZDUiLCJncm91cCI6WyJFZGl0b3JpYWxDb250ZW50U2VsZWN0aW9uLU5vblByb2QtUUEiLCJFZGl0b3JpYWxDb250ZW50UHJvY2Vzc2luZy1Ob25Qcm9kLVFBIl19.koSYCTKPAROYbaAxo-qMWW_BD4VXa0SAKnUrPMzUTAc");
		request.header("Roles", "PublicationCurator,PublicationEvaluator");
		/*	 requestParams.put("publicationId", ""); // Cast
		 requestParams.put("taskChecklistId", "301003234");
		 requestParams.put("taskChecklistLookup", "FUNDER");
		 requestParams.put("taskChecklistLookupId", "RESOLVED_CONFIRMED");			 
		 requestParams.put("taskChecklistLookupValue",  "1");
		
		 request.body(requestParams.toJSONString());*/
		 request.body(new File("./InputFiles/put.json"));
		 //int random = (int)(Math.random() * 500 + 1);
		 Response response = request.urlEncodingEnabled(false).put("9725023512");
		 System.out.println(response.prettyPrint());
	
		if(response.getStatusCode()==200||response.getStatusCode()==201)
		{
			//System.out.println("Test case Passed and reported status code is :"+response.getStatusCode());
			System.out.println("Test case Passed and reported body is :"+response.asString());
		
		}
		else
		{
			System.out.println("Test case failed and reported status code is :"+response.getStatusCode());
			System.out.println("Test case Passed and reported body is :"+response.asString());
		}
		 System.out.println(response.prettyPrint());
		 
		
		 
	}
		 
	

}
