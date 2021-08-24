package unittest;

import org.testng.Assert;

import com.zerv.database.utils.DatabaseUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetResponse {
 
	 public static void main(String[] args) throws Exception {
		
	
	 RestAssured.baseURI = "http://ccpublicationonboarding.dev-stable.cc.clarivate.com/acquisition/availableformats/";	 
	 DatabaseUtils db = new DatabaseUtils();
	 RequestSpecification httpRequest = RestAssured.given();	 
	 httpRequest.header("Content-Type", "application/json");
	 httpRequest.header("SessionToken","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMGMyMzdmMC1jN2IyLTExZTgtYmU3My02YmI4ZjU1YTNkODYiLCIxcDp0eXBlIjoiUEEiLCIxcDpwcmQiOiJhenVyZSIsIjFwOmFwcCI6ImplZGkiLCJpc3MiOiIxcCIsIjFwOmZubSI6IlBhcnRoaWJhbiIsIjFwOnRydWlkcyI6WyIxMGMyMzdmMC1jN2IyLTExZTgtYmU3My02YmI4ZjU1YTNkODYiXSwiMXA6ZW1sIjoiUGFydGhpYmFuLkdAY2xhcml2YXRlLmNvbS1DMzAwMDAzIiwiMXA6cHJvZHVjdHMiOltdLCIxcDpob3N0IjoiaHR0cHM6Ly9hY2Nlc3MuY2xhcml2YXRlLmNvbSIsIjFwOnBpZCI6IkMzMDAwMDMiLCIxcDpsbm0iOiJHIiwiMXA6dHB3ZCI6ZmFsc2UsIjFwOmxucyI6IjEwMCwxMDAiLCIxcDpsbHQiOjE1NTI1NDY0NzcwNjMsImV4cCI6MTU1MjU4OTY4MCwiaWF0IjoxNTUyNTQ2NDgwLCJqdGkiOiIxYmZlZjQ4Yi0yNGIxLTQ1NDctODNiZS0xMmZhMjhkMTdhZDUiLCJncm91cCI6WyJFZGl0b3JpYWxDb250ZW50U2VsZWN0aW9uLU5vblByb2QtUUEiLCJFZGl0b3JpYWxDb250ZW50UHJvY2Vzc2luZy1Ob25Qcm9kLVFBIl19.koSYCTKPAROYbaAxo-qMWW_BD4VXa0SAKnUrPMzUTAc");
	 httpRequest.header("Roles","PublicationEvaluator");
	 Response response = httpRequest.request(Method.GET, "147");	
	 String publicationId=response.jsonPath().getString("data.publicationId");
	 String availableLookupId=response.jsonPath().getString("data.availableFormatLookup.availableLookupId");
	 System.out.println("publicationId"+publicationId);
	System.out.println("availableLookupId"+availableLookupId);
	 
	
		    // Write code here that turns the phrase above into concrete actions
			//String expected_value=response.body().jsonPath().getString("data.availableFormatId[0]"); 
			//System.out.println("FormatID received from Response " + expected_value);
			//Thread.sleep(3000);
			 String query = "select * from AVAILABLE_FORMAT where AVAILABLE_FORMAT_ID='147'";
			 //String actual_value= db.fetchSingleColumnFromResultSet(query);
			//Assert.assertTrue(actual_value.equals(publicationId), "Record not created in Available format table");
		    
		} 
	
}
